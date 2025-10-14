import type { NextApiRequest, NextApiResponse } from 'next'
import bcrypt from 'bcryptjs'
import jwt from 'jsonwebtoken'

// In production, these should come from environment variables
const ADMIN_CREDENTIALS = {
  email: 'admin@taxiapp.com',
  password: '$2a$10$xQxKbJhF8kNxPVE5Rx8KXOZhJGm2HM9vF3Gy2v7sN6d0e.0r5PfHO' // admin123456
}

const JWT_SECRET = process.env.JWT_SECRET || 'your-jwt-secret-key'

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method !== 'POST') {
    return res.status(405).json({ success: false, message: 'Method not allowed' })
  }

  const { email, password } = req.body

  if (!email || !password) {
    return res.status(400).json({ 
      success: false, 
      message: 'Email y contraseña son requeridos' 
    })
  }

  try {
    // Check if email matches
    if (email !== ADMIN_CREDENTIALS.email) {
      return res.status(401).json({ 
        success: false, 
        message: 'Credenciales inválidas' 
      })
    }

    // Verify password
    const isValidPassword = await bcrypt.compare(password, ADMIN_CREDENTIALS.password)
    
    // For demo purposes, also accept plain password
    const isDemoPassword = password === 'admin123456'
    
    if (!isValidPassword && !isDemoPassword) {
      return res.status(401).json({ 
        success: false, 
        message: 'Credenciales inválidas' 
      })
    }

    // Create JWT token
    const token = jwt.sign(
      { 
        email: email,
        role: 'admin',
        timestamp: Date.now()
      },
      JWT_SECRET,
      { expiresIn: '24h' }
    )

    // Return success response
    res.status(200).json({
      success: true,
      message: 'Login exitoso',
      token: token,
      user: {
        email: email,
        role: 'admin',
        name: 'Administrador'
      }
    })
  } catch (error) {
    console.error('Login error:', error)
    res.status(500).json({ 
      success: false, 
      message: 'Error interno del servidor' 
    })
  }
}