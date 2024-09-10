/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
      return [
        {
          source: '/api/:path*',  // Match all API requests
          destination: 'http://localhost:8080/api/:path*',  // Proxy to backend server
        },
      ];
    },
  };
  
  export default nextConfig;
