import { NextResponse } from 'next/server';

export function middleware(request) {
  const { pathname } = request.nextUrl;
  const isAuthenticated = checkAuth(request);

  // Protect routes that require authentication
  if (pathname.startsWith('/dashboard') || pathname.startsWith('/profile')) {
    if (!isAuthenticated) {
      return NextResponse.redirect(new URL('/login', request.url));
    }
  }

  // Redirect authenticated users from login/register pages
  if ((pathname === '/login' || pathname === '/register') && isAuthenticated) {
    return NextResponse.redirect(new URL('/dashboard', request.url));
  }

  return NextResponse.next();
}

function checkAuth(request) {
  // Implement your authentication check here
  // This is a placeholder and should be replaced with your actual auth logic
  const token = request.cookies.get('auth_token');
  return !!token;
}

export const config = {
  matcher: [
    '/((?!api|_next/static|_next/image|favicon.ico).*)',
  ],
};
