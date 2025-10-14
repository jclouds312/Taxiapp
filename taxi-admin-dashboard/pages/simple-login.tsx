import { useState } from 'react'
import { useRouter } from 'next/router'

export default function SimpleLogin() {
  const router = useRouter()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)
    setError('')

    // Simple demo login
    if (email === 'admin@taxiapp.com' && password === 'admin123456') {
      localStorage.setItem('adminToken', 'demo-token-123')
      localStorage.setItem('adminUser', JSON.stringify({ email, role: 'admin' }))
      router.push('/simple-dashboard')
    } else {
      setError('Credenciales incorrectas. Use admin@taxiapp.com / admin123456')
    }
    setLoading(false)
  }

  return (
    <div style={{ 
      minHeight: '100vh', 
      background: 'linear-gradient(135deg, #f59e0b 0%, #fbbf24 100%)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      fontFamily: 'system-ui, sans-serif'
    }}>
      <div style={{ 
        background: 'white',
        borderRadius: '16px',
        padding: '32px',
        width: '100%',
        maxWidth: '400px',
        boxShadow: '0 20px 25px -5px rgba(0, 0, 0, 0.1)'
      }}>
        <div style={{ textAlign: 'center', marginBottom: '24px' }}>
          <h1 style={{ fontSize: '32px', fontWeight: 'bold', color: '#111827', margin: '0' }}>
            🚖 Taxi Admin
          </h1>
          <p style={{ color: '#6b7280', marginTop: '8px' }}>Panel de Administración</p>
        </div>

        {error && (
          <div style={{ 
            background: '#fef2f2', 
            border: '1px solid #fecaca',
            color: '#dc2626',
            padding: '12px',
            borderRadius: '8px',
            marginBottom: '16px',
            fontSize: '14px'
          }}>
            {error}
          </div>
        )}

        <form onSubmit={handleLogin}>
          <div style={{ marginBottom: '16px' }}>
            <label style={{ 
              display: 'block', 
              fontSize: '14px', 
              fontWeight: '500', 
              color: '#374151',
              marginBottom: '4px'
            }}>
              Email
            </label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="admin@taxiapp.com"
              required
              style={{ 
                width: '100%',
                padding: '8px 12px',
                border: '1px solid #d1d5db',
                borderRadius: '8px',
                fontSize: '14px',
                outline: 'none'
              }}
            />
          </div>

          <div style={{ marginBottom: '24px' }}>
            <label style={{ 
              display: 'block', 
              fontSize: '14px', 
              fontWeight: '500', 
              color: '#374151',
              marginBottom: '4px'
            }}>
              Contraseña
            </label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              required
              style={{ 
                width: '100%',
                padding: '8px 12px',
                border: '1px solid #d1d5db',
                borderRadius: '8px',
                fontSize: '14px',
                outline: 'none'
              }}
            />
          </div>

          <button
            type="submit"
            disabled={loading}
            style={{ 
              width: '100%',
              padding: '12px',
              background: loading ? '#fbbf24' : '#f59e0b',
              color: 'white',
              border: 'none',
              borderRadius: '8px',
              fontSize: '16px',
              fontWeight: '600',
              cursor: loading ? 'not-allowed' : 'pointer',
              transition: 'background 0.2s'
            }}
          >
            {loading ? 'Iniciando...' : 'Iniciar Sesión'}
          </button>

          <button
            type="button"
            onClick={() => {
              setEmail('admin@taxiapp.com')
              setPassword('admin123456')
            }}
            style={{ 
              width: '100%',
              marginTop: '12px',
              padding: '8px',
              background: 'transparent',
              color: '#f59e0b',
              border: '1px solid #f59e0b',
              borderRadius: '8px',
              fontSize: '14px',
              cursor: 'pointer',
              transition: 'background 0.2s'
            }}
          >
            Usar credenciales demo
          </button>
        </form>

        <div style={{ 
          marginTop: '32px', 
          textAlign: 'center',
          color: '#6b7280',
          fontSize: '12px'
        }}>
          <p>Simple Taxi Argentina © 2025</p>
          <p>v1.0.0</p>
        </div>
      </div>
    </div>
  )
}