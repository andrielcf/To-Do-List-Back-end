# Easy Check

## 📌 Sobre o Projeto
O **Easy Check** é uma checklist web desenvolvida para ser uma ferramenta de organização simples e acessível, independentemente do nível de conhecimento tecnológico do usuário. 

Nosso objetivo foi criar uma aplicação funcional, responsiva e intuitiva, alinhada com as necessidades dos usuários. Além de aprimorar nosso conhecimento técnico, esse projeto reforçou a importância do trabalho em equipe e do uso de metodologias ágeis no desenvolvimento de sistemas.

---

## 💻 Tecnologias Utilizadas

- **Backend:** Java Spring Boot
- **Frontend:** React + Bootstrap
- **Banco de Dados:** MySQL
- **Autenticação:** Tokens JWT
- **Deploy:** AWS
- **Configuração de CORS:** Configurado para permitir comunicação segura entre frontend e backend

---

## 📸 Imagens da Aplicação

### Tela Principal
![Image](https://github.com/user-attachments/assets/5905045f-336d-4c83-910e-105105bac338)

### Tela Principal (Mobile)
![Image](https://github.com/user-attachments/assets/c84ff316-d80e-4a9f-8aa3-ca7dd5a52cf2)

### Tela de Cadastro
![Image](https://github.com/user-attachments/assets/333b38d7-1eed-4276-b03a-bc8403fc0d35)

### Sprints
![Image](https://github.com/user-attachments/assets/f9ede321-c8db-4183-bb47-24ce57b5e077)

## 🚀 Metodologia de Desenvolvimento

Adotamos a metodologia ágil **Scrum**, utilizando:
- **Kanban** para organização e acompanhamento de tarefas
- **Sprints** para planejamento e execução iterativa do projeto
- **Conventional Commits** para manter um histórico claro e padronizado das alterações no repositório

---

## 🔗 Endpoints da API

### **AuthenticationController** (`/api/auth`)
- `POST /login` - Autentica um usuário e retorna um token JWT
- `POST /register` - Registra um novo usuário

### **ItemController** (`/api/item`)
- `POST /` - Cria um novo item
- `GET /{id}` - Retorna um item pelo ID
- `PATCH /{id}` - Atualiza um item pelo ID
- `DELETE /{id}` - Remove um item pelo ID

### **ListController** (`/api/list`)
- `POST /` - Cria uma nova lista
- `GET /all` - Retorna todas as listas
- `GET /{id}` - Retorna uma lista pelo ID
- `PATCH /{id}` - Atualiza uma lista pelo ID
- `DELETE /{id}` - Remove uma lista pelo ID

### **UserController** (`/api/user`)
- `GET /` - Retorna todos os usuários
- `GET /{id}` - Retorna um usuário pelo ID
- `DELETE /` - Remove um usuário

---
