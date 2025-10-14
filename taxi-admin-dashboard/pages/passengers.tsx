import { useState } from 'react'
import DashboardLayout from '@/components/layout/DashboardLayout'

interface Passenger {
  id: string
  name: string
  email: string
  phone: string
  registrationDate: string
  totalTrips: number
  totalSpent: number
  rating: number
  status: 'active' | 'suspended' | 'inactive'
  lastTrip: string
  paymentMethod: string
}

export default function Passengers() {
  const [passengers, setPassengers] = useState<Passenger[]>([
    {
      id: '1',
      name: 'Ana López',
      email: 'ana@example.com',
      phone: '+54 11 3456-7890',
      registrationDate: '2024-01-10',
      totalTrips: 45,
      totalSpent: 5678.90,
      rating: 4.9,
      status: 'active',
      lastTrip: '2025-10-14',
      paymentMethod: 'Tarjeta'
    },
    {
      id: '2',
      name: 'Carlos Ruiz',
      email: 'carlos@example.com',
      phone: '+54 11 4567-8901',
      registrationDate: '2024-02-15',
      totalTrips: 32,
      totalSpent: 3456.78,
      rating: 4.7,
      status: 'active',
      lastTrip: '2025-10-13',
      paymentMethod: 'Efectivo'
    },
    {
      id: '3',
      name: 'Laura Martín',
      email: 'laura@example.com',
      phone: '+54 11 5678-9012',
      registrationDate: '2024-03-20',
      totalTrips: 18,
      totalSpent: 2345.67,
      rating: 4.5,
      status: 'suspended',
      lastTrip: '2025-10-01',
      paymentMethod: 'Digital'
    }
  ])

  const [searchTerm, setSearchTerm] = useState('')
  const [filterStatus, setFilterStatus] = useState('all')
  const [sortBy, setSortBy] = useState('name')

  const filteredPassengers = passengers
    .filter(passenger => {
      const matchesSearch = passenger.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                           passenger.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
                           passenger.phone.includes(searchTerm)
      const matchesStatus = filterStatus === 'all' || passenger.status === filterStatus
      return matchesSearch && matchesStatus
    })
    .sort((a, b) => {
      if (sortBy === 'name') return a.name.localeCompare(b.name)
      if (sortBy === 'trips') return b.totalTrips - a.totalTrips
      if (sortBy === 'spent') return b.totalSpent - a.totalSpent
      if (sortBy === 'rating') return b.rating - a.rating
      return 0
    })

  const statusColors = {
    active: 'bg-green-100 text-green-800',
    suspended: 'bg-yellow-100 text-yellow-800',
    inactive: 'bg-gray-100 text-gray-800'
  }

  const statusLabels = {
    active: 'Activo',
    suspended: 'Suspendido',
    inactive: 'Inactivo'
  }

  return (
    <DashboardLayout>
      <div className="p-6">
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Gestión de Pasajeros</h1>
          <p className="text-gray-600 mt-2">Administra y monitorea a todos los usuarios de la plataforma</p>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Total Pasajeros</p>
            <p className="text-3xl font-bold text-gray-900">{passengers.length}</p>
            <p className="text-xs text-green-600 mt-1">+12% este mes</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Activos</p>
            <p className="text-3xl font-bold text-green-600">
              {passengers.filter(p => p.status === 'active').length}
            </p>
            <p className="text-xs text-gray-500 mt-1">Con viajes recientes</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Rating Promedio</p>
            <p className="text-3xl font-bold text-gray-900">4.7 ⭐</p>
            <p className="text-xs text-gray-500 mt-1">De todos los usuarios</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Ingresos Totales</p>
            <p className="text-3xl font-bold text-gray-900">$89.5K</p>
            <p className="text-xs text-green-600 mt-1">+8% vs mes anterior</p>
          </div>
        </div>

        {/* Filters and Search */}
        <div className="bg-white rounded-lg shadow p-4 mb-6">
          <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
            <div className="flex items-center space-x-4">
              <input
                type="text"
                placeholder="Buscar pasajero..."
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <select
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500"
                value={filterStatus}
                onChange={(e) => setFilterStatus(e.target.value)}
              >
                <option value="all">Todos los estados</option>
                <option value="active">Activos</option>
                <option value="suspended">Suspendidos</option>
                <option value="inactive">Inactivos</option>
              </select>
              <select
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500"
                value={sortBy}
                onChange={(e) => setSortBy(e.target.value)}
              >
                <option value="name">Ordenar por nombre</option>
                <option value="trips">Ordenar por viajes</option>
                <option value="spent">Ordenar por gastos</option>
                <option value="rating">Ordenar por rating</option>
              </select>
            </div>
            <button className="px-4 py-2 bg-yellow-600 text-white rounded-lg hover:bg-yellow-700 transition">
              + Agregar Pasajero
            </button>
          </div>
        </div>

        {/* Passengers Table */}
        <div className="bg-white rounded-lg shadow overflow-hidden">
          <table className="w-full">
            <thead className="bg-gray-50 border-b border-gray-200">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Pasajero
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Contacto
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Viajes
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Gastos
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Estado
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Rating
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Acciones
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {filteredPassengers.map((passenger) => (
                <tr key={passenger.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div>
                      <div className="text-sm font-medium text-gray-900">{passenger.name}</div>
                      <div className="text-sm text-gray-500">Desde {passenger.registrationDate}</div>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm text-gray-900">{passenger.email}</div>
                    <div className="text-sm text-gray-500">{passenger.phone}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm text-gray-900">{passenger.totalTrips} viajes</div>
                    <div className="text-sm text-gray-500">Último: {passenger.lastTrip}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm font-medium text-gray-900">
                      ${passenger.totalSpent.toFixed(2)}
                    </div>
                    <div className="text-sm text-gray-500">{passenger.paymentMethod}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className={`px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${statusColors[passenger.status]}`}>
                      {statusLabels[passenger.status]}
                    </span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="flex items-center">
                      <span className="text-sm text-gray-900">{passenger.rating}</span>
                      <span className="ml-1">⭐</span>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <button className="text-blue-600 hover:text-blue-900 mr-3">Ver</button>
                    <button className="text-yellow-600 hover:text-yellow-900 mr-3">Editar</button>
                    {passenger.status === 'active' ? (
                      <button className="text-red-600 hover:text-red-900">Suspender</button>
                    ) : (
                      <button className="text-green-600 hover:text-green-900">Activar</button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Pagination */}
        <div className="mt-6 flex items-center justify-between">
          <p className="text-sm text-gray-700">
            Mostrando <span className="font-medium">{filteredPassengers.length}</span> de{' '}
            <span className="font-medium">{passengers.length}</span> pasajeros
          </p>
          <div className="flex items-center space-x-2">
            <button className="px-3 py-1 border border-gray-300 rounded hover:bg-gray-50">
              Anterior
            </button>
            <button className="px-3 py-1 bg-yellow-600 text-white rounded">1</button>
            <button className="px-3 py-1 border border-gray-300 rounded hover:bg-gray-50">2</button>
            <button className="px-3 py-1 border border-gray-300 rounded hover:bg-gray-50">3</button>
            <button className="px-3 py-1 border border-gray-300 rounded hover:bg-gray-50">
              Siguiente
            </button>
          </div>
        </div>
      </div>
    </DashboardLayout>
  )
}