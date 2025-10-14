export default function ActiveDrivers() {
  const drivers = [
    {
      id: '1',
      name: 'Juan Pérez',
      vehicle: 'Toyota Corolla',
      plate: 'ABC 123',
      location: 'Palermo',
      status: 'available',
      trips: 12,
      earnings: 1450.50
    },
    {
      id: '2',
      name: 'María García',
      vehicle: 'Honda Civic',
      plate: 'DEF 456',
      location: 'Centro',
      status: 'busy',
      trips: 8,
      earnings: 980.25
    },
    {
      id: '3',
      name: 'Pedro Sánchez',
      vehicle: 'Ford Focus',
      plate: 'GHI 789',
      location: 'Recoleta',
      status: 'available',
      trips: 15,
      earnings: 1780.00
    },
    {
      id: '4',
      name: 'Luis Rodríguez',
      vehicle: 'Chevrolet Cruze',
      plate: 'JKL 012',
      location: 'Belgrano',
      status: 'offline',
      trips: 6,
      earnings: 650.75
    }
  ]

  const statusColors = {
    available: 'bg-green-500',
    busy: 'bg-yellow-500',
    offline: 'bg-gray-500'
  }

  return (
    <div className="bg-white rounded-lg shadow">
      <div className="px-6 py-4 border-b border-gray-200">
        <h3 className="text-lg font-semibold text-gray-900">Conductores Activos</h3>
      </div>
      
      <div className="divide-y divide-gray-200">
        {drivers.map((driver) => (
          <div key={driver.id} className="px-6 py-4 hover:bg-gray-50">
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-3">
                <div className={`w-3 h-3 rounded-full ${statusColors[driver.status as keyof typeof statusColors]}`}></div>
                <div>
                  <p className="text-sm font-medium text-gray-900">{driver.name}</p>
                  <p className="text-xs text-gray-500">{driver.vehicle} • {driver.plate}</p>
                </div>
              </div>
              <div className="text-right">
                <p className="text-sm font-medium text-gray-900">${driver.earnings.toFixed(2)}</p>
                <p className="text-xs text-gray-500">{driver.trips} viajes hoy</p>
              </div>
            </div>
            <div className="mt-2 flex items-center text-xs text-gray-500">
              <svg className="w-3 h-3 mr-1" fill="currentColor" viewBox="0 0 20 20">
                <path fillRule="evenodd" d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z" clipRule="evenodd" />
              </svg>
              {driver.location}
            </div>
          </div>
        ))}
      </div>
      
      <div className="px-6 py-3 border-t border-gray-200">
        <button className="text-sm text-yellow-600 hover:text-yellow-700 font-medium">
          Ver todos los conductores →
        </button>
      </div>
    </div>
  )
}