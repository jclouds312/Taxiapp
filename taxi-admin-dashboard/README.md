# 🚖 Taxi Admin Dashboard

Dashboard administrativo completo para gestionar el sistema de taxis de Simple Taxi Argentina.

## 🚀 Características

### Dashboard Principal
- 📊 Estadísticas en tiempo real
- 📈 Gráficos de ingresos y viajes
- 🚗 Monitoreo de conductores activos
- 📍 Tracking de viajes en curso

### Gestión de Conductores
- ✅ Alta, baja y modificación
- 📋 Historial de viajes
- ⭐ Sistema de calificaciones
- 💰 Control de pagos y comisiones

### Gestión de Pasajeros
- 👥 Registro y administración
- 📱 Historial de viajes
- 💳 Métodos de pago
- 🎯 Segmentación y análisis

### Sistema de Viajes
- 🗺️ Monitoreo en tiempo real
- 📍 Tracking GPS
- 💵 Cálculo automático de tarifas
- 📊 Reportes y estadísticas

### Configuración
- 🏷️ Gestión de tarifas
- 📍 Configuración de zonas
- 💰 Comisiones y pagos
- 🔔 Notificaciones

## 🛠️ Tecnologías

- **Framework**: Next.js 14
- **Lenguaje**: TypeScript
- **Estilos**: Tailwind CSS
- **Base de datos**: PostgreSQL
- **Autenticación**: JWT
- **Gráficos**: Custom Components
- **API**: RESTful

## 📦 Instalación

```bash
# Instalar dependencias
npm install

# Configurar variables de entorno
cp .env.local.example .env.local

# Ejecutar en desarrollo
npm run dev

# Compilar para producción
npm run build

# Ejecutar en producción
npm start
```

## 🔧 Configuración

### Variables de Entorno

```env
# Base de datos
DATABASE_URL=postgresql://user:password@localhost:5432/taxi_admin

# JWT
JWT_SECRET=your-super-secret-jwt-key
NEXTAUTH_SECRET=your-super-secret-nextauth-key

# API
API_URL=http://localhost:3000/api
BACKEND_API_URL=http://techlabz.in

# Admin por defecto
ADMIN_EMAIL=admin@taxiapp.com
ADMIN_PASSWORD=admin123456
```

## 🚀 Despliegue en Vercel

1. **Fork el repositorio** en GitHub

2. **Importar en Vercel**:
   - Ve a [vercel.com](https://vercel.com)
   - Click en "New Project"
   - Importa el repositorio
   - Configura las variables de entorno

3. **Variables de Entorno en Vercel**:
   ```
   DATABASE_URL=tu_url_de_base_de_datos
   JWT_SECRET=genera_una_clave_segura
   NEXTAUTH_SECRET=genera_otra_clave_segura
   ```

4. **Deploy**:
   - Click en "Deploy"
   - Espera a que termine el build
   - ¡Tu dashboard está listo!

## 📱 Páginas Disponibles

- `/` - Dashboard principal
- `/login` - Página de login
- `/drivers` - Gestión de conductores
- `/passengers` - Gestión de pasajeros
- `/trips` - Gestión de viajes
- `/payments` - Sistema de pagos
- `/zones` - Configuración de zonas
- `/rates` - Configuración de tarifas
- `/reports` - Reportes y estadísticas
- `/settings` - Configuración general

## 🔒 Seguridad

- Autenticación JWT
- Encriptación de contraseñas con bcrypt
- Protección CSRF
- Headers de seguridad
- Rate limiting en API

## 📊 API Endpoints

### Autenticación
- `POST /api/auth/login` - Login de administrador
- `POST /api/auth/logout` - Cerrar sesión
- `GET /api/auth/verify` - Verificar token

### Estadísticas
- `GET /api/stats` - Estadísticas generales
- `GET /api/stats/revenue` - Estadísticas de ingresos
- `GET /api/stats/trips` - Estadísticas de viajes

### Conductores
- `GET /api/drivers` - Lista de conductores
- `GET /api/drivers/:id` - Detalle de conductor
- `POST /api/drivers` - Crear conductor
- `PUT /api/drivers/:id` - Actualizar conductor
- `DELETE /api/drivers/:id` - Eliminar conductor

### Pasajeros
- `GET /api/passengers` - Lista de pasajeros
- `GET /api/passengers/:id` - Detalle de pasajero
- `POST /api/passengers` - Crear pasajero
- `PUT /api/passengers/:id` - Actualizar pasajero
- `DELETE /api/passengers/:id` - Eliminar pasajero

### Viajes
- `GET /api/trips` - Lista de viajes
- `GET /api/trips/:id` - Detalle de viaje
- `POST /api/trips` - Crear viaje
- `PUT /api/trips/:id` - Actualizar viaje
- `DELETE /api/trips/:id` - Cancelar viaje

## 🎨 Personalización

El dashboard utiliza Tailwind CSS para los estilos. Puedes personalizar:

- **Colores**: Edita `tailwind.config.js`
- **Componentes**: En la carpeta `/components`
- **Layouts**: En `/components/layout`
- **Páginas**: En la carpeta `/pages`

## 📝 Licencia

MIT License - Simple Taxi Argentina © 2025

## 🤝 Soporte

Para soporte y consultas:
- Email: admin@taxiapp.com
- GitHub: [github.com/jclouds312/taxi-admin-dashboard](https://github.com/jclouds312/taxi-admin-dashboard)

---

**Desarrollado con ❤️ para Simple Taxi Argentina**