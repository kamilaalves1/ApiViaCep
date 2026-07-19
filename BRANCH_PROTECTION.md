# 🛡️ Branch Protection Rules

Configuração de proteção para a branch `main`.

## 📋 Regras Aplicadas

### ✅ Require pull request reviews
- Mínimo 1 review obrigatório
- Dismiss stale reviews com novo commit

### ✅ Require status checks
- CI - Testes e Cobertura
- jacoco:check (≥85%)

### ✅ Require branches up to date
- Força sincronização com main

### ✅ Require conversation resolution
- Todas conversas resolvidas antes de merge

### ✅ Merge options
- Apenas Squash merge permitido
- Delete head branch automaticamente

## 🔧 Configurar no GitHub

1. Settings → Branches
2. Add rule → main
3. Marcar todas as opções acima
4. Save changes

## 📊 Resultado

- ✅ Qualidade de código garantida
- ✅ Testes sempre passam
- ✅ Cobertura mantida em 85%
- ✅ Revisão de código obrigatória
