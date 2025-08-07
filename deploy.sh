#!/bin/bash

echo "🚀 Digital Video Store - Deployment Helper"
echo "=========================================="

# Check if we're in the right directory
if [ ! -f "package.json" ] && [ ! -d "frontend" ]; then
    echo "❌ Please run this script from your project root directory"
    exit 1
fi

echo "📦 Installing Vercel CLI..."
npm install -g vercel

echo "📁 Moving to frontend directory..."
cd frontend

echo "📦 Installing frontend dependencies..."
npm install

echo "🔨 Building frontend for production..."
npm run build

echo "✅ Frontend build complete!"
echo ""
echo "🌐 Next steps:"
echo "1. Push your code to GitHub"
echo "2. Go to vercel.com and import your repository"
echo "3. Set root directory to 'frontend'"
echo "4. Add environment variable: REACT_APP_API_URL"
echo "5. Deploy!"
echo ""
echo "📖 See VERCEL_DEPLOYMENT_GUIDE.md for detailed instructions"
