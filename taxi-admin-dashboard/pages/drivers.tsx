import { useState, useEffect } from 'react'
import DashboardLayout from '@/components/layout/DashboardLayout'

interface Driver {
  id: string
  name: string
  email: string
  phone: string
  vehicle: string
  licensePlate: string
  status: 'active' | 'inactive' | 'busy'
  rating: number
  totalTrips: number
  joinedDate: string
}

export default function Drivers() {
  const [drivers, setDrivers] = useState<Driver[]>([
    {
      id: '1',
      name: 'Juan Pérez',
      email: 'juan@example.com',
      phone: '+54 11 1234-5678',
      vehicle: 'Toyota Corolla 2020',
      licensePlate: 'ABC 123',
      status: 'active',
      rating: 4.8,
      totalTrips: 1234,
      joinedDate: '2024-01-15'
    },
    {
      id: '2',
      name: 'María García',
      email: 'maria@example.com',
      phone: '+54 11 2345-6789',
      vehicle: 'Honda Civic 2021',
      licensePlate: 'DEF 456',
      status: 'busy',
      rating: 4.9,
      totalTrips: 987,
      joinedDate: '2024-02-20'
    },
    // Add more mock data as needed
  ])

  const [searchTerm, setSearchTerm] = useState('')
  const [filterStatus, setFilterStatus] = useState('all')
  const [showAddModal, setShowAddModal] = useState(false)

  const filteredDrivers = drivers.filter(driver => {
    const matchesSearch = driver.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         driver.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
                         driver.licensePlate.toLowerCase().includes(searchTerm.toLowerCase())
    const matchesStatus = filterStatus === 'all' || driver.status === filterStatus
    return matchesSearch && matchesStatus
  })

  const statusColors = {
    active: 'bg-green-100 text-green-800',
    inactive: 'bg-gray-100 text-gray-800',
    busy: 'bg-yellow-100 text-yellow-800'
  }

  const statusLabels = {
    active: 'Activo',
    inactive: 'Inactivo',
    busy: 'Ocupado'
  }

  const handleToggleStatus = async (driverId: string) => {
    setDrivers(prevDrivers => 
      prevDrivers.map(driver => 
        driver.id === driverId 
          ? { ...driver, status: driver.status === 'active' ? 'inactive' : 'active' as 'active' | 'inactive' }
          : driver
      )
    )
  }

  return (
    <DashboardLayout>
      <div className="p-6">
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Gestión de Conductores</h1>
          <p className="text-gray-600 mt-2">Administra y monitorea a todos los conductores de la plataforma</p>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Total Conductores</p>
            <p className="text-3xl font-bold text-gray-900">{drivers.length}</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Activos Ahora</p>
            <p className="text-3xl font-bold text-green-600">
              {drivers.filter(d => d.status === 'active').length}
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">En Viaje</p>
            <p className="text-3xl font-bold text-yellow-600">
              {drivers.filter(d => d.status === 'busy').length}
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Rating Promedio</p>
            <p className="text-3xl font-bold text-gray-900">4.85 ⭐</p>
          </div>
        </div>

        {/* Filters and Search */}
        <div className="bg-white rounded-lg shadow p-4 mb-6">
          <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
            <div className="flex items-center space-x-4">
              <input
                type="text"
                placeholder="Buscar conductor..."
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
                <option value="inactive">Inactivos</option>
                <option value="busy">En viaje</option>
              </select>
            </div>
            <button
              onClick={() => setShowAddModal(true)}
              className="px-4 py-2 bg-yellow-600 text-white rounded-lg hover:bg-yellow-700 transition"
            >
              + Agregar Conductor
            </button>
          </div>
        </div>

        {/* Drivers Table */}
        <div className="bg-white rounded-lg shadow overflow-hidden">
          <table className="w-full">
            <thead className="bg-gray-50 border-b border-gray-200">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Conductor
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Vehículo
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Estado
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Rating
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Viajes
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Acciones
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {filteredDrivers.map((driver) => (
                <tr key={driver.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div>
                      <div className="text-sm font-medium text-gray-900">{driver.name}</div>
                      <div className="text-sm text-gray-500">{driver.email}</div>
                      <div className="text-sm text-gray-500">{driver.phone}</div>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm text-gray-900">{driver.vehicle}</div>
                    <div className="text-sm text-gray-500">{driver.licensePlate}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className={`px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${statusColors[driver.status]}`}>
                      {statusLabels[driver.status]}
                    </span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="flex items-center">
                      <span className="text-sm text-gray-900">{driver.rating}</span>
                      <span className="ml-1">⭐</span>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                    {driver.totalTrips}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <button className="text-blue-600 hover:text-blue-900 mr-3">Ver</button>
                    <button className="text-yellow-600 hover:text-yellow-900 mr-3">Editar</button>
                    <button 
                      onClick={() => handleToggleStatus(driver.id)}
                      className="text-red-600 hover:text-red-900"
                    >
                      {driver.status === 'active' ? 'Desactivar' : 'Activar'}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </DashboardLayout>
  )
}