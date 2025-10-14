import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import DashboardLayout from '@/components/layout/DashboardLayout'
import StatsCard from '@/components/dashboard/StatsCard'
import RevenueChart from '@/components/charts/RevenueChart'
import TripChart from '@/components/charts/TripChart'
import RecentTrips from '@/components/dashboard/RecentTrips'
import ActiveDrivers from '@/components/dashboard/ActiveDrivers'

interface DashboardStats {
  totalDrivers: number
  activeDrivers: number
  totalPassengers: number
  totalTrips: number
  todayTrips: number
  todayRevenue: number
  weeklyRevenue: number
  monthlyRevenue: number
}

export default function Dashboard() {
  const router = useRouter()
  const [stats, setStats] = useState<DashboardStats>({
    totalDrivers: 245,
    activeDrivers: 156,
    totalPassengers: 1847,
    totalTrips: 8459,
    todayTrips: 234,
    todayRevenue: 4567.89,
    weeklyRevenue: 28945.67,
    monthlyRevenue: 98765.43
  })

  const [loading, setLoading] = useState(false)

  useEffect(() => {
    // Check authentication
    const token = localStorage.getItem('adminToken')
    if (!token) {
      router.push('/login')
    }
    
    // Load dashboard data
    loadDashboardData()
  }, [router])

  const loadDashboardData = async () => {
    setLoading(true)
    try {
      const response = await fetch('/api/stats')
      const data = await response.json()
      if (data.success) {
        setStats(data.stats)
      }
    } catch (error) {
      console.error('Error loading dashboard data:', error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <DashboardLayout>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-gray-900 mb-8">Dashboard Principal</h1>
        
        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <StatsCard
            title="Conductores Activos"
            value={stats.activeDrivers}
            total={stats.totalDrivers}
            icon="users"
            color="blue"
            percentage={Math.round((stats.activeDrivers / stats.totalDrivers) * 100)}
          />
          <StatsCard
            title="Viajes Hoy"
            value={stats.todayTrips}
            icon="car"
            color="green"
            trend="+12%"
          />
          <StatsCard
            title="Ingresos Hoy"
            value={`$${stats.todayRevenue.toFixed(2)}`}
            icon="dollar"
            color="yellow"
            trend="+8%"
          />
          <StatsCard
            title="Total Pasajeros"
            value={stats.totalPassengers}
            icon="users"
            color="purple"
            trend="+23%"
          />
        </div>

        {/* Charts Row */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-8">
          <RevenueChart />
          <TripChart />
        </div>

        {/* Tables Row */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          <RecentTrips />
          <ActiveDrivers />
        </div>
      </div>
    </DashboardLayout>
  )
}