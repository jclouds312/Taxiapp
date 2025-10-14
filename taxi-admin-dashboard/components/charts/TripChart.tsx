export default function TripChart() {
  const hours = [
    { hour: '00', trips: 12 },
    { hour: '04', trips: 8 },
    { hour: '08', trips: 45 },
    { hour: '12', trips: 62 },
    { hour: '16', trips: 58 },
    { hour: '20', trips: 72 },
  ]

  const maxTrips = Math.max(...hours.map(h => h.trips))

  return (
    <div className="bg-white rounded-lg shadow p-6">
      <div className="mb-4">
        <h3 className="text-lg font-semibold text-gray-900">Viajes por Hora</h3>
        <p className="text-sm text-gray-600">Distribución hoy</p>
      </div>
      
      <div className="flex items-end justify-between h-48 mb-2">
        {hours.map((item) => (
          <div key={item.hour} className="flex-1 mx-1 flex flex-col items-center">
            <div className="w-full bg-gray-200 rounded-t" style={{ height: '100%' }}>
              <div 
                className="w-full bg-green-500 rounded-t transition-all duration-500"
                style={{ 
                  height: `${(item.trips / maxTrips) * 100}%`,
                  marginTop: `${100 - (item.trips / maxTrips) * 100}%`
                }}
              />
            </div>
            <span className="text-xs text-gray-600 mt-2">{item.hour}h</span>
            <span className="text-xs font-medium text-gray-900">{item.trips}</span>
          </div>
        ))}
      </div>
      
      <div className="mt-4 pt-4 border-t border-gray-200 grid grid-cols-2 gap-4">
        <div>
          <p className="text-xs text-gray-600">Hora Pico</p>
          <p className="text-sm font-semibold text-gray-900">20:00 - 21:00</p>
        </div>
        <div>
          <p className="text-xs text-gray-600">Total Hoy</p>
          <p className="text-sm font-semibold text-gray-900">257 viajes</p>
        </div>
      </div>
    </div>
  )
}