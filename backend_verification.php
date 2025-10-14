<?php
/**
 * Script de verificación del backend Simple Taxi
 * Verifica la conexión con el servidor techlabz.in
 */

$base_url = "http://techlabz.in";
$api_version = "/ladriver/Webservices_driver";

echo "═══════════════════════════════════════════════════════════\n";
echo "     🔍 VERIFICACIÓN BACKEND - SIMPLE TAXI                 \n";
echo "═══════════════════════════════════════════════════════════\n\n";

// Verificar conexión básica
echo "📡 Verificando conexión con el servidor...\n";
$ch = curl_init($base_url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_TIMEOUT, 10);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
$response = curl_exec($ch);
$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
curl_close($ch);

if ($http_code == 200 || $http_code == 301 || $http_code == 302) {
    echo "✅ Servidor principal activo (HTTP $http_code)\n";
} else {
    echo "❌ Error conectando al servidor (HTTP $http_code)\n";
}

// Endpoints para verificar
$endpoints = [
    'Login' => '/login',
    'Registration' => '/registration',
    'Driver Status' => '/get_driver_status',
    'Trip List' => '/trip_list_for_today',
    'Weekly Earnings' => '/weekly_earnings'
];

echo "\n📋 Verificando endpoints principales:\n";
foreach ($endpoints as $name => $endpoint) {
    $url = $base_url . $api_version . $endpoint . "?test=1";
    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_TIMEOUT, 5);
    curl_setopt($ch, CURLOPT_NOBODY, true);
    curl_exec($ch);
    $code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    curl_close($ch);
    
    if ($code > 0) {
        echo "  • $name: ";
        if ($code == 200 || $code == 400 || $code == 401) {
            echo "✅ Activo (HTTP $code)\n";
        } else {
            echo "⚠️ Estado desconocido (HTTP $code)\n";
        }
    } else {
        echo "  • $name: ❌ Sin respuesta\n";
    }
}

// Verificar estructura de respuesta
echo "\n🔧 Verificando estructura de API:\n";
$test_url = $base_url . $api_version . "/app_status?version=1.0";
$ch = curl_init($test_url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_TIMEOUT, 10);
$response = curl_exec($ch);
$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
curl_close($ch);

if ($response) {
    $json = json_decode($response, true);
    if ($json) {
        echo "✅ API devuelve JSON válido\n";
        if (isset($json['status'])) {
            echo "✅ Estructura de respuesta correcta\n";
        }
    } else {
        echo "⚠️ Respuesta no es JSON válido\n";
    }
} else {
    echo "❌ No se pudo obtener respuesta del API\n";
}

echo "\n═══════════════════════════════════════════════════════════\n";
echo "📊 RESUMEN:\n";
echo "  • URL Base: $base_url\n";
echo "  • API Path: $api_version\n";
echo "  • Estado: ";
if ($http_code == 200 || $http_code > 0) {
    echo "✅ BACKEND ACTIVO\n";
} else {
    echo "❌ BACKEND NO DISPONIBLE\n";
}
echo "═══════════════════════════════════════════════════════════\n";
?>