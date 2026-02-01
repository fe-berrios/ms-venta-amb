# Microservicio: ms-venta-amb (Ambassador)

## Descripción General
**ms-venta-amb** es el microservicio "embajador" o "adaptador" que maneja la comunicación directa con la API de **Webpay Plus de Transbank**. Actúa como una capa de abstracción que aísla al sistema interno de los detalles técnicos y cambios en la API externa de Webpay.

## Puerto de Ejecución
- **Puerto:** `8082`

## Responsabilidades Principales

### 1. Comunicación con Webpay Plus
- Gestiona todas las interacciones con la API REST de Transbank
- Maneja autenticación con API keys de Webpay
- Adapta formatos de datos entre sistema interno y Webpay

### 2. Gestión de Credenciales
- Almacena y maneja las credenciales de Webpay (API Key ID y Secret)
- Aplica las credenciales correctas según tipo de operación
- Aísla al sistema de cambios en autenticación de Webpay

## Comunicación con Otros Microservicios

### Recibe Llamadas Desde:
1. **ms-venta-bs (Business Service - Puerto 8081)**
   - `POST /add/venta` - Crear nueva transacción en Webpay
   - `PUT /confirm/venta/{token_ws}` - Confirmar transacción existente
   - `GET /status/venta/{buy_order}` - Consultar estado de transacción
   - `PUT /capture/venta/{token}` - Capturar transacción autorizada

### Realiza Llamadas Hacia:
1. **Webpay Plus API de Transbank**
   - Entorno de integración: `https://webpay3gint.transbank.cl`
   - Opera con certificados de prueba (no producción real)

### Flujo de Comunicación:
```
Sistema Interno → ms-venta-amb → Webpay API
      ↓              ↑
  Respuesta Interna ← Respuesta Webpay
```

## Endpoints Disponibles

### Endpoints Principales:
- **POST `/add/venta`** - Crea transacción en Webpay
  - Llama a: `POST /rswebpaytransaction/api/webpay/v1.2/transactions`
  - Devuelve: `VentaResponse` con `token` y `url`

- **PUT `/confirm/venta/{token_ws}`** - Confirma transacción
  - Llama a: `PUT /rswebpaytransaction/api/webpay/v1.2/transactions/{token}`
  - Devuelve: `StatusResponse` completo

- **GET `/status/venta/{buy_order}`** - Consulta estado
  - Llama a: `GET /rswebpaytransaction/api/webpay/v1.2/transactions/{token}`
  - Devuelve: `StatusResponse` actual

- **PUT `/capture/venta/{token}`** - Captura transacción
  - Llama a: `PUT /rswebpaytransaction/api/webpay/v1.2/transactions/{token}/capture`
  - Devuelve: `CaptureResponse`

## Modelos de Datos

### Recibe del Sistema Interno:
- **`VentaRequest`**: Datos básicos para creación
- **`CaptureRequest`**: Datos para captura de transacción

### Devuelve al Sistema Interno:
- **`VentaResponse`**: Token y URL de Webpay
- **`StatusResponse`**: Estado completo de transacción
- **`CaptureResponse`**: Resultado de captura

### Comunica a Webpay:
- Formatos JSON específicos de Transbank
- Headers de autenticación requeridos

## Credenciales de Webpay

### Configuradas en `application.properties`:
```properties
api.key.id=597055555532           # API Key ID para transacciones
api.key.id.capture=597055555540   # API Key ID específica para capturas
api.key.secret=579B532A7440BB0C9079DED94D31EA1615BACEB56610332264630D42D0A36B1C
```

### Headers Enviados a Webpay:
- `Tbk-Api-Key-Id`: API Key ID según operación
- `Tbk-Api-Key-Secret`: API Key Secret compartida

## Operaciones de Webpay Implementadas

### 1. Creación de Transacción
- Inicia flujo de pago en Webpay
- Genera `token_ws` único
- Retorna URL para redirección al formulario de pago

### 2. Confirmación de Transacción
- Confirma pago después de redirección desde formulario
- Obtiene estado final (AUTHORIZED, FAILED, etc.)
- Recibe detalles de tarjeta y autorización

### 3. Consulta de Estado
- Consulta estado actual sin confirmar
- Útil para monitoreo y reconciliación

### 4. Captura de Transacción
- Captura fondos de transacción autorizada
- Requiere `authorization_code` y monto específico

## Flujo Técnico de una Llamada

### Ejemplo: Crear Transacción
```
1. ms-venta-bs → POST /add/venta (con VentaRequest)
2. ms-venta-amb construye headers con API keys
3. ms-venta-amb → POST a Webpay API
4. Webpay valida y crea transacción
5. Webpay → Respuesta JSON a ms-venta-amb
6. ms-venta-amb mapea a VentaResponse
7. ms-venta-amb → VentaResponse a ms-venta-bs
```

Este microservicio actúa como **puente seguro y adaptativo** entre el sistema interno de ventas y el proveedor de pagos externo (Transbank). Su diseño como "embajador" sigue el patrón Ambassador del sidecar pattern, proporcionando:
