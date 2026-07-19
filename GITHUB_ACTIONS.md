# 🚀 GitHub Actions Setup - API VIA CEP

Configuração automática de CI/CD usando GitHub Actions.

## 📋 Workflows

### 1. Tests (tests.yml)
- Executa testes com Maven
- Gera relatório de cobertura JaCoCo
- Valida cobertura mínima de 85%
- Acionador: Push/PR em main ou develop

### 2. Docker (docker.yml)
- Build da imagem Docker
- Tagueia com latest e SHA
- Acionador: Push em main ou tags

### 3. Security (security.yml)
- CodeQL analysis
- Trivy vulnerability scan
- Agendado: Toda segunda-feira

## ✅ Configuração

1. Workflows criados em `.github/workflows/`
2. Template de PR em `.github/pull_request_template.md`
3. Branch protection habilitado em Settings → Branches

## 📊 Badges

```markdown
[![Tests](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/tests.yml/badge.svg)](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/tests.yml)
[![Docker](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/docker.yml/badge.svg)](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/docker.yml)
[![Security](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/security.yml/badge.svg)](https://github.com/kamilaalves1/ApiViaCep/actions/workflows/security.yml)
```

## 🎯 Próximos Passos

1. Configurar Branch Protection em Settings
2. Adicionar badges ao README
3. Fazer push para testar workflows
