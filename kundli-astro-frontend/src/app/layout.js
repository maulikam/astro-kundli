// src/app/layout.js
import '../styles/globals.css';

import "bootstrap/dist/css/bootstrap.min.css";
import "../assets/css/animate.min.css";
import "../assets/scss/light-bootstrap-dashboard-react.scss?v=2.0.0";
import "../assets/css/demo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import { AuthProvider } from '../context/AuthContext';

export default function RootLayout({ children }) {
  return (
    <html lang="en">  
      <body> 
        <AuthProvider>
          {children}
        </AuthProvider>
      </body>
     </html>
  );
}
