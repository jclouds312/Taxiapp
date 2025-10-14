import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'

export default function SimpleDashboard() {
  const router = useRouter()
  const [selectedSection, setSelectedSection] = useState('dashboard')

  useEffect(() => {
    const token = localStorage.getItem('adminToken')
    if (!token) {
      router.push('/simple-login')
    }
  }, [router])

  const handleLogout = () => {
    localStorage.removeItem('adminToken')
    localStorage.removeItem('adminUser')
    router.push('/simple-login')
  }

  const stats = {
    totalDrivers: 245,
    activeDrivers: 156,
    totalPassengers: 1847,
    totalTrips: 8459,
    todayRevenue: 4567.89,
    weeklyRevenue: 28945.67
  }

  const renderContent = () => {
    switch(selectedSection) {
      case 'dashboard':
        return (
          <div>
            <h2 style={{ fontSize: '28px', fontWeight: 'bold', marginBottom: '24px' }}>
              Dashboard Principal
            </h2>
            
            <div style={{ 
              display: 'grid',
              gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
              gap: '20px',
              marginBottom: '32px'
            }}>
              <StatCard 
                title="Conductores Activos"
                value={`${stats.activeDrivers}/${stats.totalDrivers}`}
                icon="🚗"
                color="#3b82f6"
              />
              <StatCard 
                title="Pasajeros"
                value={stats.totalPassengers}
                icon="👥"
                color="#8b5cf6"
              />
              <StatCard 
                title="Viajes Totales"
                value={stats.totalTrips}
                icon="🗺️"
                color="#10b981"
              />
              <StatCard 
                title="Ingresos Hoy"
                value={`$${stats.todayRevenue}`}
                icon="💰"
                color="#f59e0b"
              />
              <StatCard 
                title="Ingresos Semanales"
                value={`$${stats.weeklyRevenue}`}
                icon="📈"
                color="#ef4444"
              />
            </div>

            <div style={{ background: 'white', padding: '24px', borderRadius: '12px', boxShadow: '0 1px 3px rgba(0,0,0,0.1)' }}>
              <h3 style={{ fontSize: '18px', fontWeight: '600', marginBottom: '16px' }}>Actividad Reciente</h3>
              <div style={{ color: '#6b7280' }}>
                <p>• Nuevo conductor registrado: Juan Pérez (hace 2 horas)</p>
                <p>• Viaje completado: #1234 - $125.50 (hace 3 horas)</p>
                <p>• Pasajero nuevo: Ana López (hace 4 horas)</p>
                <p>• Conductor desactivado: Pedro García (hace 5 horas)</p>
              </div>
            </div>
          </div>
        )
      
      case 'drivers':
        return (
          <div>
            <h2 style={{ fontSize: '28px', fontWeight: 'bold', marginBottom: '24px' }}>
              Gestión de Conductores
            </h2>
            <SimpleTable 
              headers={['ID', 'Nombre', 'Vehículo', 'Estado', 'Viajes', 'Rating']}
              data={[
                ['#001', 'Juan Pérez', 'Toyota Corolla ABC-123', 'Activo', '234', '4.8 ⭐'],
                ['#002', 'María García', 'Honda Civic DEF-456', 'Ocupado', '189', '4.9 ⭐'],
                ['#003', 'Pedro Sánchez', 'Ford Focus GHI-789', 'Inactivo', '156', '4.7 ⭐'],
                ['#004', 'Luis Rodríguez', 'Chevrolet Cruze JKL-012', 'Activo', '298', '4.9 ⭐']
              ]}
            />
          </div>
        )
      
      case 'passengers':
        return (
          <div>
            <h2 style={{ fontSize: '28px', fontWeight: 'bold', marginBottom: '24px' }}>
              Gestión de Pasajeros
            </h2>
            <SimpleTable 
              headers={['ID', 'Nombre', 'Email', 'Viajes', 'Gasto Total', 'Estado']}
              data={[
                ['#101', 'Ana López', 'ana@email.com', '45', '$2,345.67', 'Activo'],
                ['#102', 'Carlos Ruiz', 'carlos@email.com', '32', '$1,890.45', 'Activo'],
                ['#103', 'Laura Martín', 'laura@email.com', '18', '$987.32', 'Suspendido'],
                ['#104', 'Sofía Díaz', 'sofia@email.com', '67', '$3,456.78', 'Activo']
              ]}
            />
          </div>
        )
      
      case 'trips':
        return (
          <div>
            <h2 style={{ fontSize: '28px', fontWeight: 'bold', marginBottom: '24px' }}>
              Gestión de Viajes
            </h2>
            <SimpleTable 
              headers={['ID', 'Conductor', 'Pasajero', 'Origen → Destino', 'Tarifa', 'Estado']}
              data={[
                ['#2001', 'Juan Pérez', 'Ana López', 'Centro → Palermo', '$125.50', 'Completado'],
                ['#2002', 'María García', 'Carlos Ruiz', 'Recoleta → Belgrano', '$89.00', 'En curso'],
                ['#2003', 'Pedro Sánchez', 'Laura Martín', 'Núñez → Puerto Madero', '$156.75', 'Pendiente'],
                ['#2004', 'Luis Rodríguez', 'Sofía Díaz', 'Caballito → San Telmo', '$78.90', 'Completado']
              ]}
            />
          </div>
        )
      
      default:
        return null
    }
  }

  return (
    <div style={{ 
      minHeight: '100vh',
      background: '#f3f4f6',
      fontFamily: 'system-ui, sans-serif',
      display: 'flex'
    }}>
      {/* Sidebar */}
      <div style={{ 
        width: '250px',
        background: '#1f2937',
        color: 'white',
        padding: '20px'
      }}>
        <h1 style={{ fontSize: '24px', fontWeight: 'bold', marginBottom: '32px' }}>
          🚖 Taxi Admin
        </h1>
        
        <nav>
          {[
            { id: 'dashboard', label: 'Dashboard', icon: '📊' },
            { id: 'drivers', label: 'Conductores', icon: '🚗' },
            { id: 'passengers', label: 'Pasajeros', icon: '👥' },
            { id: 'trips', label: 'Viajes', icon: '🗺️' }
          ].map(item => (
            <button
              key={item.id}
              onClick={() => setSelectedSection(item.id)}
              style={{ 
                display: 'block',
                width: '100%',
                padding: '12px 16px',
                marginBottom: '8px',
                background: selectedSection === item.id ? '#f59e0b' : 'transparent',
                color: 'white',
                border: 'none',
                borderRadius: '8px',
                textAlign: 'left',
                cursor: 'pointer',
                fontSize: '16px',
                transition: 'background 0.2s'
              }}
            >
              {item.icon} {item.label}
            </button>
          ))}
        </nav>

        <button
          onClick={handleLogout}
          style={{ 
            position: 'absolute',
            bottom: '20px',
            left: '20px',
            right: '20px',
            padding: '12px',
            background: '#dc2626',
            color: 'white',
            border: 'none',
            borderRadius: '8px',
            cursor: 'pointer',
            fontSize: '16px',
            fontWeight: '500'
          }}
        >
          🚪 Cerrar Sesión
        </button>
      </div>

      {/* Main Content */}
      <div style={{ flex: 1, padding: '32px' }}>
        <div style={{ 
          maxWidth: '1200px',
          margin: '0 auto'
        }}>
          {renderContent()}
        </div>
      </div>
    </div>
  )
}

function StatCard({ title, value, icon, color }: any) {
  return (
    <div style={{ 
      background: 'white',
      padding: '20px',
      borderRadius: '12px',
      boxShadow: '0 1px 3px rgba(0,0,0,0.1)'
    }}>
      <div style={{ 
        display: 'flex',
        alignItems: 'center',
        marginBottom: '12px'
      }}>
        <div style={{ 
          width: '48px',
          height: '48px',
          background: `${color}20`,
          borderRadius: '8px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          fontSize: '24px',
          marginRight: '12px'
        }}>
          {icon}
        </div>
        <div style={{ flex: 1 }}>
          <p style={{ 
            fontSize: '14px',
            color: '#6b7280',
            margin: 0
          }}>
            {title}
          </p>
          <p style={{ 
            fontSize: '24px',
            fontWeight: 'bold',
            color: '#111827',
            margin: 0
          }}>
            {value}
          </p>
        </div>
      </div>
    </div>
  )
}

function SimpleTable({ headers, data }: any) {
  return (
    <div style={{ 
      background: 'white',
      borderRadius: '12px',
      overflow: 'hidden',
      boxShadow: '0 1px 3px rgba(0,0,0,0.1)'
    }}>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr style={{ background: '#f9fafb' }}>
            {headers.map((header: string, index: number) => (
              <th key={index} style={{ 
                padding: '12px',
                textAlign: 'left',
                fontSize: '14px',
                fontWeight: '600',
                color: '#374151',
                borderBottom: '1px solid #e5e7eb'
              }}>
                {header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row: string[], rowIndex: number) => (
            <tr key={rowIndex} style={{ borderBottom: '1px solid #e5e7eb' }}>
              {row.map((cell: string, cellIndex: number) => (
                <td key={cellIndex} style={{ 
                  padding: '12px',
                  fontSize: '14px',
                  color: '#111827'
                }}>
                  {cell}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}