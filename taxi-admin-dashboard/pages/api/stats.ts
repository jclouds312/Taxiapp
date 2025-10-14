import type { NextApiRequest, NextApiResponse } from 'next'

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method !== 'GET') {
    return res.status(405).json({ success: false, message: 'Method not allowed' })
  }

  // Mock data - in production, this would come from your database
  const stats = {
    totalDrivers: 245,
    activeDrivers: 156,
    totalPassengers: 1847,
    totalTrips: 8459,
    todayTrips: 234,
    todayRevenue: 4567.89,
    weeklyRevenue: 28945.67,
    monthlyRevenue: 98765.43,
    avgTripDistance: 12.5,
    avgTripDuration: 18.3,
    peakHours: '18:00-20:00',
    topZones: ['Centro', 'Palermo', 'Recoleta'],
    revenueGrowth: 12.5,
    tripsGrowth: 8.3,
    newDriversThisWeek: 12,
    newPassengersThisWeek: 89
  }

  res.status(200).json({
    success: true,
    stats: stats,
    timestamp: new Date().toISOString()
  })
}