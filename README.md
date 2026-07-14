# VIA CEP API

Uma API REST em Java/Spring Boot para consultar endereços através do serviço VIA CEP.

## 📋 Requisitos

- Java 17+
- Maven 3.8+

## 🚀 Instalação

### 1. Clonar o repositório
```bash
git clone https://github.com/kamilaalves1/ApiViaCep.git
cd ApiViaCep/viacep-api
```

### 2. Compilar o projeto
```bash
mvn clean install
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`

## 📚 Endpoints

### Buscar CEP
Retorna os dados de endereço para um CEP específico.

**Requisição:**
```
GET /api/cep/{cep}
```

**Exemplo:**
```bash
curl http://localhost:8080/api/cep/01310-100
```

**Resposta (Sucesso - 200):**
```json
{
  "success": true,
  "data": {
    "cep": "01310-100",
    "logradouro": "Avenida Paulista",
    "complemento": "",
    "bairro": "Bela Vista",
    "localidade": "São Paulo",
    "uf": "SP",
    "ibge": "3550308",
    "gia": "",
    "ddd": "11",
    "siafi": "7107"
  },
  "message": "CEP encontrado com sucesso"
}
```

**Resposta (Erro - 400):**
```json
{
  "success": false,
  "error": "CEP deve conter exatamente 8 dígitos"
}
```

### Validar CEP
Valida se um CEP é válido.

**Requisição:**
```
GET /api/cep/validate/{cep}
```

**Exemplo:**
```bash
curl http://localhost:8080/api/cep/validate/01310-100
```

**Resposta (CEP válido - 200):**
```json
{
  "success": true,
  "data": true,
  "message": "CEP válido"
}
```

**Resposta (CEP inválido - 400):**
```json
{
  "success": false,
  "data": false,
  "error": "CEP não encontrado"
}
```

## ✅ Validações

A API realiza as seguintes validações:

- ✓ CEP não pode ser vazio
- ✓ CEP deve conter exatamente 8 dígitos
- ✓ CEP deve conter apenas dígitos (caracteres especiais como hífen são removidos automaticamente)
- ✓ Verifica se o CEP existe no banco de dados do VIA CEP

## 🧪 Testes

Executar todos os testes:
```bash
mvn test
```

Executar testes com cobertura:
```bash
mvn clean test jacoco:report
```

## 📦 Estrutura do Projeto

```
viacep-api/
├── src/
│   ├── main/
│   │   ├── java/com/viacep/
│   │   │   ├── config/          # Configurações da aplicação
│   │   │   ├── controller/       # Controllers REST
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── exception/        # Exceções customizadas e handlers
│   │   │   ├── service/          # Serviços de negócio
│   │   │   └── ViacepApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/viacep/
│           ├── controller/
│           └── service/
├── pom.xml
└── README.md
```

## 🛠️ Dependências

- **Spring Boot** 3.2.3: Framework Web
- **Lombok**: Redução de boilerplate
- **Jackson**: Serialização JSON
- **JUnit 5**: Testes
- **Mockito**: Mocks para testes

## ⚙️ Configuração

O arquivo `application.properties` contém as configurações padrão:

```properties
spring.application.name=viacep-api
server.port=8080
logging.level.root=INFO
logging.level.com.viacep=DEBUG
```

## 🌐 Integração com Outras Aplicações

Para integrar esta API em suas aplicações:

### JavaScript/TypeScript
```javascript
const buscarCep = async (cep) => {
  const response = await fetch(`http://localhost:8080/api/cep/${cep}`);
  const data = await response.json();
  
  if (data.success) {
    console.log(data.data);
  } else {
    console.error(data.error);
  }
};
```

### Python
```python
import requests

def buscar_cep(cep):
    response = requests.get(f"http://localhost:8080/api/cep/{cep}")
    data = response.json()
    
    if data['success']:
        return data['data']
    else:
        raise Exception(data['error'])
```

## 🔗 API Original

Esta aplicação consome a API pública do VIA CEP:
- **URL**: https://viacep.com.br/ws/{cep}/json/
- **Documentação**: https://viacep.com.br/

## 📝 Licença

Este projeto está sob a licença MIT.

## 👤 Autor

Kamila Alves
- GitHub: [@kamilaalves1](https://github.com/kamilaalves1)
- Email: kamilaalves1@gmail.com

## 🤝 Contribuições

Contribuições são bem-vindas! Por favor, sinta-se livre para abrir uma issue ou enviar um pull request.
