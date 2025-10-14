export default function RecentTrips() {
  const trips = [
    {
      id: '1',
      driver: 'Juan Pérez',
      passenger: 'Ana López',
      from: 'Centro',
      to: 'Palermo',
      status: 'completed',
      amount: 125.50,
      time: '10:25 AM'
    },
    {
      id: '2',
      driver: 'María García',
      passenger: 'Carlos Ruiz',
      from: 'Recoleta',
      to: 'Belgrano',
      status: 'in_progress',
      amount: 89.00,
      time: '10:15 AM'
    },
    {
      id: '3',
      driver: 'Pedro Sánchez',
      passenger: 'Laura Martín',
      from: 'Núñez',
      to: 'Puerto Madero',
      status: 'completed',
      amount: 156.75,
      time: '10:05 AM'
    },
    {
      id: '4',
      driver: 'Luis Rodríguez',
      passenger: 'Sofía Díaz',
      from: 'Caballito',
      to: 'San Telmo',
      status: 'cancelled',
      amount: 0,
      time: '09:55 AM'
    }
  ]

  const statusColors = {
    completed: 'bg-green-100 text-green-800',
    in_progress: 'bg-yellow-100 text-yellow-800',
    cancelled: 'bg-red-100 text-red-800'
  }

  const statusLabels = {
    completed: 'Completado',
    in_progress: 'En curso',
    cancelled: 'Cancelado'
  }

  return (
    <div className="bg-white rounded-lg shadow">
      <div className="px-6 py-4 border-b border-gray-200">
        <h3 className="text-lg font-semibold text-gray-900">Viajes Recientes</h3>
      </div>
      
      <div className="overflow-x-auto">
        <table className="w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Hora</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Conductor</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Ruta</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Estado</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Monto</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {trips.map((trip) => (
              <tr key={trip.id} className="hover:bg-gray-50">
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                  {trip.time}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                  {trip.driver}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-600">
                  {trip.from} → {trip.to}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <span className={`px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full ${statusColors[trip.status as keyof typeof statusColors]}`}>
                    {statusLabels[trip.status as keyof typeof statusLabels]}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                  ${trip.amount.toFixed(2)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      <div className="px-6 py-3 border-t border-gray-200">
        <button className="text-sm text-yellow-600 hover:text-yellow-700 font-medium">
          Ver todos los viajes →
        </button>
      </div>
    </div>
  )
}