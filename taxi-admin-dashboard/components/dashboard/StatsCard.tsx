interface StatsCardProps {
  title: string
  value: string | number
  icon?: string
  color?: 'blue' | 'green' | 'yellow' | 'red' | 'purple'
  trend?: string
  total?: number
  percentage?: number
}

export default function StatsCard({ 
  title, 
  value, 
  icon, 
  color = 'blue', 
  trend,
  total,
  percentage 
}: StatsCardProps) {
  const colorClasses = {
    blue: 'bg-blue-500',
    green: 'bg-green-500',
    yellow: 'bg-yellow-500',
    red: 'bg-red-500',
    purple: 'bg-purple-500'
  }

  const iconMap: { [key: string]: string } = {
    users: '👥',
    car: '🚗',
    dollar: '💵',
    chart: '📈',
    clock: '⏰'
  }

  return (
    <div className="bg-white rounded-lg shadow p-6">
      <div className="flex items-center justify-between mb-4">
        <div className={`w-12 h-12 ${colorClasses[color]} bg-opacity-20 rounded-lg flex items-center justify-center text-2xl`}>
          {icon ? iconMap[icon] || '📊' : '📊'}
        </div>
        {trend && (
          <span className={`text-sm font-medium ${trend.startsWith('+') ? 'text-green-600' : 'text-red-600'}`}>
            {trend}
          </span>
        )}
      </div>
      
      <h3 className="text-gray-600 text-sm font-medium mb-1">{title}</h3>
      <p className="text-2xl font-bold text-gray-900">{value}</p>
      
      {total && (
        <p className="text-sm text-gray-500 mt-1">de {total} total</p>
      )}
      
      {percentage !== undefined && (
        <div className="mt-3">
          <div className="w-full bg-gray-200 rounded-full h-2">
            <div 
              className={`${colorClasses[color]} h-2 rounded-full`}
              style={{ width: `${percentage}%` }}
            />
          </div>
          <p className="text-xs text-gray-500 mt-1">{percentage}% activos</p>
        </div>
      )}
    </div>
  )
}