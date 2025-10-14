import { useState } from 'react'
import DashboardLayout from '@/components/layout/DashboardLayout'

interface Trip {
  id: string
  driver: string
  driverPhone: string
  passenger: string
  passengerPhone: string
  from: string
  to: string
  distance: number
  duration: number
  fare: number
  status: 'pending' | 'in_progress' | 'completed' | 'cancelled'
  startTime: string
  endTime?: string
  paymentMethod: string
  rating?: number
}

export default function Trips() {
  const [trips, setTrips] = useState<Trip[]>([
    {
      id: '001',
      driver: 'Juan Pérez',
      driverPhone: '+54 11 1234-5678',
      passenger: 'Ana López',
      passengerPhone: '+54 11 3456-7890',
      from: 'Centro - Av. Corrientes 1234',
      to: 'Palermo - Av. Santa Fe 4567',
      distance: 8.5,
      duration: 25,
      fare: 245.50,
      status: 'completed',
      startTime: '2025-10-14 10:30',
      endTime: '2025-10-14 10:55',
      paymentMethod: 'Tarjeta',
      rating: 5
    },
    {
      id: '002',
      driver: 'María García',
      driverPhone: '+54 11 2345-6789',
      passenger: 'Carlos Ruiz',
      passengerPhone: '+54 11 4567-8901',
      from: 'Recoleta - Av. Callao 890',
      to: 'Belgrano - Av. Cabildo 2345',
      distance: 6.2,
      duration: 18,
      fare: 189.75,
      status: 'in_progress',
      startTime: '2025-10-14 11:15',
      paymentMethod: 'Efectivo'
    },
    {
      id: '003',
      driver: 'Pedro Sánchez',
      driverPhone: '+54 11 3456-7890',
      passenger: 'Laura Martín',
      passengerPhone: '+54 11 5678-9012',
      from: 'Núñez - Av. del Libertador 6789',
      to: 'Puerto Madero - Alicia Moreau 123',
      distance: 12.3,
      duration: 35,
      fare: 356.90,
      status: 'pending',
      startTime: '2025-10-14 11:30',
      paymentMethod: 'Digital'
    }
  ])

  const [filterStatus, setFilterStatus] = useState('all')
  const [searchTerm, setSearchTerm] = useState('')
  const [dateFilter, setDateFilter] = useState('today')

  const filteredTrips = trips.filter(trip => {
    const matchesSearch = 
      trip.driver.toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.passenger.toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.from.toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.to.toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.id.includes(searchTerm)
    const matchesStatus = filterStatus === 'all' || trip.status === filterStatus
    return matchesSearch && matchesStatus
  })

  const statusColors = {
    pending: 'bg-blue-100 text-blue-800',
    in_progress: 'bg-yellow-100 text-yellow-800',
    completed: 'bg-green-100 text-green-800',
    cancelled: 'bg-red-100 text-red-800'
  }

  const statusLabels = {
    pending: 'Pendiente',
    in_progress: 'En Curso',
    completed: 'Completado',
    cancelled: 'Cancelado'
  }

  const handleCancelTrip = (tripId: string) => {
    setTrips(prevTrips => 
      prevTrips.map(trip => 
        trip.id === tripId 
          ? { ...trip, status: 'cancelled' as const }
          : trip
      )
    )
  }

  return (
    <DashboardLayout>
      <div className="p-6">
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900">Gestión de Viajes</h1>
          <p className="text-gray-600 mt-2">Monitorea y administra todos los viajes en tiempo real</p>
        </div>

        {/* Stats Cards */}
        <div className="grid grid-cols-1 md:grid-cols-5 gap-6 mb-8">
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Viajes Hoy</p>
            <p className="text-3xl font-bold text-gray-900">234</p>
            <p className="text-xs text-green-600 mt-1">+12% vs ayer</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">En Curso</p>
            <p className="text-3xl font-bold text-yellow-600">
              {trips.filter(t => t.status === 'in_progress').length}
            </p>
            <p className="text-xs text-gray-500 mt-1">Activos ahora</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Pendientes</p>
            <p className="text-3xl font-bold text-blue-600">
              {trips.filter(t => t.status === 'pending').length}
            </p>
            <p className="text-xs text-gray-500 mt-1">Por asignar</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Completados</p>
            <p className="text-3xl font-bold text-green-600">
              {trips.filter(t => t.status === 'completed').length}
            </p>
            <p className="text-xs text-gray-500 mt-1">Hoy</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <p className="text-gray-600 text-sm">Ingresos Hoy</p>
            <p className="text-3xl font-bold text-gray-900">$4,567</p>
            <p className="text-xs text-green-600 mt-1">+8% vs ayer</p>
          </div>
        </div>

        {/* Filters */}
        <div className="bg-white rounded-lg shadow p-4 mb-6">
          <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
            <div className="flex items-center space-x-4">
              <input
                type="text"
                placeholder="Buscar viaje, conductor o pasajero..."
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500 w-80"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
              <select
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500"
                value={filterStatus}
                onChange={(e) => setFilterStatus(e.target.value)}
              >
                <option value="all">Todos los estados</option>
                <option value="pending">Pendientes</option>
                <option value="in_progress">En curso</option>
                <option value="completed">Completados</option>
                <option value="cancelled">Cancelados</option>
              </select>
              <select
                className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-yellow-500"
                value={dateFilter}
                onChange={(e) => setDateFilter(e.target.value)}
              >
                <option value="today">Hoy</option>
                <option value="yesterday">Ayer</option>
                <option value="week">Esta semana</option>
                <option value="month">Este mes</option>
              </select>
            </div>
            <div className="flex items-center space-x-2">
              <button className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition">
                Exportar CSV
              </button>
              <button className="px-4 py-2 bg-yellow-600 text-white rounded-lg hover:bg-yellow-700 transition">
                + Nuevo Viaje
              </button>
            </div>
          </div>
        </div>

        {/* Trips Table */}
        <div className="bg-white rounded-lg shadow overflow-hidden">
          <table className="w-full">
            <thead className="bg-gray-50 border-b border-gray-200">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  ID
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Conductor
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Pasajero
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Ruta
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Detalles
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Tarifa
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Estado
                </th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                  Acciones
                </th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {filteredTrips.map((trip) => (
                <tr key={trip.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                    #{trip.id}
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div>
                      <div className="text-sm font-medium text-gray-900">{trip.driver}</div>
                      <div className="text-xs text-gray-500">{trip.driverPhone}</div>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div>
                      <div className="text-sm font-medium text-gray-900">{trip.passenger}</div>
                      <div className="text-xs text-gray-500">{trip.passengerPhone}</div>
                    </div>
                  </td>
                  <td className="px-6 py-4">
                    <div className="text-sm">
                      <div className="text-gray-900 font-medium">Desde:</div>
                      <div className="text-gray-600 text-xs">{trip.from}</div>
                      <div className="text-gray-900 font-medium mt-1">Hasta:</div>
                      <div className="text-gray-600 text-xs">{trip.to}</div>
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-sm">
                      <div className="text-gray-600">
                        <span className="font-medium">Distancia:</span> {trip.distance} km
                      </div>
                      <div className="text-gray-600">
                        <span className="font-medium">Duración:</span> {trip.duration} min
                      </div>
                      <div className="text-gray-600">
                        <span className="font-medium">Pago:</span> {trip.paymentMethod}
                      </div>
                      {trip.rating && (
                        <div className="text-gray-600">
                          <span className="font-medium">Rating:</span> {trip.rating} ⭐
                        </div>
                      )}
                    </div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <div className="text-lg font-bold text-gray-900">${trip.fare.toFixed(2)}</div>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap">
                    <span className={`px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${statusColors[trip.status]}`}>
                      {statusLabels[trip.status]}
                    </span>
                  </td>
                  <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <div className="flex flex-col space-y-1">
                      <button className="text-blue-600 hover:text-blue-900">Ver Mapa</button>
                      <button className="text-yellow-600 hover:text-yellow-900">Editar</button>
                      {(trip.status === 'pending' || trip.status === 'in_progress') && (
                        <button 
                          onClick={() => handleCancelTrip(trip.id)}
                          className="text-red-600 hover:text-red-900"
                        >
                          Cancelar
                        </button>
                      )}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Map Section - Placeholder */}
        <div className="mt-6 bg-white rounded-lg shadow p-6">
          <h3 className="text-lg font-semibold text-gray-900 mb-4">Viajes en Tiempo Real</h3>
          <div className="bg-gray-200 rounded-lg h-96 flex items-center justify-center">
            <div className="text-center">
              <p className="text-gray-600 text-lg mb-2">🗺️ Mapa de Viajes</p>
              <p className="text-gray-500 text-sm">Integración con Google Maps pendiente</p>
            </div>
          </div>
        </div>
      </div>
    </DashboardLayout>
  )
}