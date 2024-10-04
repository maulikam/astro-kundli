#!/bin/bash

# Logging functions
log_info() {
    echo "[INFO] $1"
}

log_warning() {
    echo "[WARNING] $1"
}

log_error() {
    echo "[ERROR] $1" >&2
}

# Function to create a directory if it doesn't exist
create_dir() {
    if [ ! -d "$1" ]; then
        mkdir -p "$1"
        log_info "Created directory: $1"
    else
        log_warning "Directory already exists: $1"
    fi
}

# Function to create a file if it doesn't exist
create_file() {
    local dir
    dir=$(dirname "$1")
    if [ ! -d "$dir" ]; then
        mkdir -p "$dir"
        log_info "Created directory: $dir"
    fi

    if [ ! -f "$1" ]; then
        touch "$1"
        log_info "Created file: $1"
    else
        log_warning "File already exists: $1"
    fi
}

# Constants for common paths
ROOT_DIR="kundli-astro-frontend"
SRC="$ROOT_DIR/src"
APP="$SRC/app"
COMPONENTS="$SRC/components"
LIB="$SRC/lib"

# Directories to be created
dirs=(
    "$APP"
    "$APP/login"
    "$APP/register"
    "$APP/dashboard"
    "$APP/kundli"
    "$APP/kundli/[id]"
    "$APP/matchmaking"
    "$APP/horoscope/daily"
    "$APP/horoscope/monthly"
    "$APP/horoscope/yearly"
    "$APP/charts"
    "$APP/api/users"
    "$APP/api/kundli/[id]"
    "$SRC/assets/images"
    "$SRC/assets/icons"
    "$COMPONENTS/forms"
    "$COMPONENTS/ui"
    "$SRC/context"
    "$SRC/data"
    "$SRC/hooks"
    "$LIB/constants"
    "$SRC/locales/en"
    "$SRC/locales/hi"
    "$SRC/services"
    "$SRC/store"
    "$SRC/styles"
    "$SRC/types"
    "$SRC/utils/validation"
    "$SRC/utils/formatting"
    "$ROOT_DIR/public/images"
    "$ROOT_DIR/public/icons"
    "$ROOT_DIR/public/fonts"
    "$ROOT_DIR/tests/components"
    "$ROOT_DIR/tests/hooks"
    "$ROOT_DIR/tests/unit"
    "$ROOT_DIR/tests/integration"
    "$ROOT_DIR/tests/e2e"
    "$ROOT_DIR/tests/coverage"
    "$ROOT_DIR/tests/__mocks__"
    "$ROOT_DIR/.github/workflows"
    "$ROOT_DIR/.husky"
)

# Files to be created
files=(
    "$APP/layout.js"
    "$APP/page.js"
    "$APP/globals.css"
    "$APP/login/page.js"
    "$APP/register/page.js"
    "$APP/dashboard/layout.js"
    "$APP/dashboard/page.js"
    "$APP/kundli/layout.js"
    "$APP/kundli/page.js"
    "$APP/kundli/[id]/page.js"
    "$APP/matchmaking/page.js"
    "$APP/horoscope/daily/page.js"
    "$APP/horoscope/monthly/page.js"
    "$APP/horoscope/yearly/page.js"
    "$APP/charts/layout.js"
    "$APP/charts/page.js"
    "$APP/api/users/route.js"
    "$APP/api/kundli/[id]/route.js"
    "$COMPONENTS/forms/RegisterForm.js"
    "$COMPONENTS/forms/KundliForm.js"
    "$COMPONENTS/forms/MatchmakingForm.js"
    "$COMPONENTS/ui/Button.js"
    "$COMPONENTS/ui/Modal.js"
    "$COMPONENTS/ui/InputField.js"
    "$COMPONENTS/ErrorBoundary.js"
    "$COMPONENTS/Header.js"
    "$COMPONENTS/Footer.js"
    "$COMPONENTS/ChartDisplay.js"
    "$COMPONENTS/HoroscopeDisplay.js"
    "$COMPONENTS/Loading.js"
    "$COMPONENTS/Error.js"
    "$SRC/context/AuthContext.js"
    "$SRC/context/KundliContext.js"
    "$SRC/data/zodiacSigns.js"
    "$SRC/data/planets.js"
    "$SRC/hooks/useAuth.js"
    "$SRC/hooks/useKundli.js"
    "$SRC/hooks/useFetch.js"
    "$SRC/hooks/useUserApi.js"
    "$SRC/hooks/useKundliApi.js"
    "$LIB/api.js"
    "$LIB/utils.js"
    "$LIB/authGuard.js"
    "$LIB/axiosInstance.js"
    "$LIB/logService.js"
    "$LIB/seo.js"
    "$LIB/serviceWorker.js"
    "$LIB/analytics.js"
    "$LIB/accessibility.js"
    "$LIB/constants/apiEndpoints.js"
    "$LIB/constants/errorMessages.js"
    "$SRC/locales/en/common.json"
    "$SRC/locales/hi/common.json"
    "$SRC/services/userService.js"
    "$SRC/services/kundliService.js"
    "$SRC/store/userSlice.js"
    "$SRC/store/kundliSlice.js"
    "$SRC/store/index.js"
    "$SRC/styles/globals.css"
    "$SRC/styles/components.css"
    "$SRC/types/User.ts"
    "$SRC/types/Kundli.ts"
    "$SRC/utils/validation/kundliValidation.js"
    "$SRC/utils/validation/userValidation.js"
    "$SRC/utils/formatting/dateFormatter.js"
    "$SRC/utils/formatting/currencyFormatter.js"
    "$SRC/middleware.js"
    "$ROOT_DIR/public/sitemap.xml"
    "$ROOT_DIR/public/robots.txt"
    "$ROOT_DIR/tests/__mocks__/mockData.js"
    "$ROOT_DIR/.github/workflows/ci.yml"
    "$ROOT_DIR/.github/workflows/deployment.yml"
    "$ROOT_DIR/.husky/pre-commit"
    "$ROOT_DIR/.env.local"
    "$ROOT_DIR/.env.development"
    "$ROOT_DIR/.env.production"
    "$ROOT_DIR/.env.test"
    "$ROOT_DIR/.eslintrc.js"
    "$ROOT_DIR/.prettierrc"
    "$ROOT_DIR/.gitignore"
    "$ROOT_DIR/tailwind.config.js"
    "$ROOT_DIR/jest.config.js"
    "$ROOT_DIR/next.config.js"
    "$ROOT_DIR/tsconfig.json"
    "$ROOT_DIR/package.json"
    "$ROOT_DIR/README.md"
    "$ROOT_DIR/CHANGELOG.md"
    "$ROOT_DIR/Dockerfile"
    "$ROOT_DIR/docker-compose.yml"
)

# Create directories and files with verification
missing=0
for dir in "${dirs[@]}"; do
    create_dir "$dir"
    if [ ! -d "$dir" ]; then
        log_error "Missing directory: $dir"
        missing=$((missing + 1))
    fi
done

for file in "${files[@]}"; do
    create_file "$file"
    if [ ! -f "$file" ]; then
        log_error "Missing file: $file"
        missing=$((missing + 1))
    fi
done

if [ $missing -eq 0 ]; then
    log_info "All directories and files have been created successfully!"
else
    log_warning "$missing items are missing. Please check the logs above."
fi
