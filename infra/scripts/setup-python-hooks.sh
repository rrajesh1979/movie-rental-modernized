#!/bin/bash

# Exit on error
set -e

echo "Setting up Python pre-commit hooks..."

# Install Python dev dependencies
pip install -r api/requirements-dev.txt

# Install pre-commit if not already installed
if ! command -v pre-commit &> /dev/null; then
    echo "Installing pre-commit..."
    pip install pre-commit
fi

# Install the pre-commit hooks
pre-commit install

echo "Pre-commit hooks have been set up successfully!"
echo "You can now run 'pre-commit run --all-files' to test all files" 