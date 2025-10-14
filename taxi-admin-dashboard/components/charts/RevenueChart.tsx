import { useEffect, useState } from 'react'

export default function RevenueChart() {
  const [data, setData] = useState([
    { day: 'Lun', revenue: 3200 },
    { day: 'Mar', revenue: 4100 },
    { day: 'Mié', revenue: 3800 },
    { day: 'Jue', revenue: 4500 },
    { day: 'Vie', revenue: 5200 },
    { day: 'Sáb', revenue: 6800 },
    { day: 'Dom', revenue: 5900 }
  ])

  const maxRevenue = Math.max(...data.map(d => d.revenue))

  return (
    <div className="bg-white rounded-lg shadow p-6">
      <div className="mb-4">
        <h3 className="text-lg font-semibold text-gray-900">Ingresos Semanales</h3>
        <p className="text-sm text-gray-600">Últimos 7 días</p>
      </div>
      
      <div className="space-y-2">
        {data.map((item) => (
          <div key={item.day} className="flex items-center">
            <span className="w-12 text-sm text-gray-600">{item.day}</span>
            <div className="flex-1 mx-4">
              <div className="h-8 bg-gray-200 rounded-full overflow-hidden">
                <div 
                  className="h-full bg-yellow-500 rounded-full"
                  style={{ width: `${(item.revenue / maxRevenue) * 100}%` }}
                />
              </div>
            </div>
            <span className="text-sm font-medium text-gray-900">
              ${item.revenue.toLocaleString()}
            </span>
          </div>
        ))}
      </div>
      
      <div className="mt-4 pt-4 border-t border-gray-200">
        <div className="flex justify-between items-center">
          <span className="text-sm text-gray-600">Total Semana</span>
          <span className="text-xl font-bold text-gray-900">
            ${data.reduce((sum, item) => sum + item.revenue, 0).toLocaleString()}
          </span>
        </div>
      </div>
    </div>
  )
}