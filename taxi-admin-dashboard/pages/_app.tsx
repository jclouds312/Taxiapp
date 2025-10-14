import '@/styles/globals.css'
import type { AppProps } from 'next/app'
import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'

export default function App({ Component, pageProps }: AppProps) {
  const router = useRouter()
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  useEffect(() => {
    // Check authentication
    const token = localStorage.getItem('adminToken')
    if (token) {
      setIsAuthenticated(true)
    } else if (router.pathname !== '/login') {
      router.push('/login')
    }
  }, [router])

  return <Component {...pageProps} />
}