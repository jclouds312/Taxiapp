import { useState } from 'react'
import { useRouter } from 'next/router'
import Link from 'next/link'

interface DashboardLayoutProps {
  children: React.ReactNode
}

export default function DashboardLayout({ children }: DashboardLayoutProps) {
  const router = useRouter()
  const [sidebarOpen, setSidebarOpen] = useState(true)

  const menuItems = [
    { label: 'Dashboard', href: '/', icon: '📊' },
    { label: 'Conductores', href: '/drivers', icon: '🚗' },
    { label: 'Pasajeros', href: '/passengers', icon: '👥' },
    { label: 'Viajes', href: '/trips', icon: '🗺️' },
    { label: 'Pagos', href: '/payments', icon: '💳' },
    { label: 'Zonas', href: '/zones', icon: '📍' },
    { label: 'Tarifas', href: '/rates', icon: '💰' },
    { label: 'Reportes', href: '/reports', icon: '📈' },
    { label: 'Configuración', href: '/settings', icon: '⚙️' },
  ]

  const handleLogout = () => {
    localStorage.removeItem('adminToken')
    localStorage.removeItem('adminUser')
    router.push('/login')
  }

  return (
    <div className="min-h-screen bg-gray-100 flex">
      {/* Sidebar */}
      <aside className={`${sidebarOpen ? 'w-64' : 'w-20'} bg-gray-900 text-white transition-all duration-300`}>
        <div className="p-4">
          <div className="flex items-center justify-between mb-8">
            <h1 className={`font-bold text-xl ${sidebarOpen ? 'block' : 'hidden'}`}>
              🚖 Taxi Admin
            </h1>
            <button
              onClick={() => setSidebarOpen(!sidebarOpen)}
              className="text-white hover:bg-gray-800 p-2 rounded"
            >
              {sidebarOpen ? '◀' : '▶'}
            </button>
          </div>
          
          <nav className="space-y-2">
            {menuItems.map((item) => (
              <Link
                key={item.href}
                href={item.href}
                className={`flex items-center space-x-3 px-4 py-3 rounded-lg hover:bg-gray-800 transition ${
                  router.pathname === item.href ? 'bg-yellow-600' : ''
                }`}
              >
                <span className="text-xl">{item.icon}</span>
                {sidebarOpen && <span>{item.label}</span>}
              </Link>
            ))}
          </nav>
        </div>
        
        <div className="absolute bottom-0 w-full p-4">
          <button
            onClick={handleLogout}
            className="w-full flex items-center justify-center space-x-2 px-4 py-3 bg-red-600 hover:bg-red-700 rounded-lg transition"
          >
            <span>🚪</span>
            {sidebarOpen && <span>Cerrar Sesión</span>}
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1">
        {/* Top Bar */}
        <header className="bg-white shadow-sm border-b border-gray-200">
          <div className="px-6 py-4 flex items-center justify-between">
            <h2 className="text-xl font-semibold text-gray-800">
              Sistema de Administración
            </h2>
            
            <div className="flex items-center space-x-4">
              {/* Notifications */}
              <button className="relative p-2 hover:bg-gray-100 rounded-full">
                <span className="text-xl">🔔</span>
                <span className="absolute top-0 right-0 w-2 h-2 bg-red-500 rounded-full"></span>
              </button>
              
              {/* User Profile */}
              <div className="flex items-center space-x-2">
                <div className="w-10 h-10 bg-yellow-500 rounded-full flex items-center justify-center text-white font-bold">
                  A
                </div>
                <span className="font-medium text-gray-700">Admin</span>
              </div>
            </div>
          </div>
        </header>

        {/* Page Content */}
        <div className="overflow-y-auto h-[calc(100vh-64px)]">
          {children}
        </div>
      </main>
    </div>
  )
}