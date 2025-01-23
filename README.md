# Twiit - Plataforma pessoal

> Twiit é um desenvolvimento pessoal de como funcionaria o back-end de uma aplicação de mídia social como o Instagram e o Facebook por exemplo. O projeto conta com escopos de segurança e autenticação utilizando geração de tokens
através do JWT e do OAuth2, suas funcionalidades são as necessárias para que este fluxo funcione corretamente. A aplicação tem as principais funcionalidades:
> - Um usuário administrador pode criar outros usuários;
> - Um usuário pode efetuar um login e assim terá um token de autenticação;
> - Todos os usuários logados consegue gerar tweets (Verificacao através do token quando efetuado o login);
> - Todos os usuários logados conseguem ver todos os tweets criados (com paginação)
> - Somente o usuário quem criou o seu tweet, pode deletá-lo;
> - Um usuário Administrador pode criar outros usuários Administradores;
> - Um usuário básico não pode criar usuários;


## 💻 Pré-requisitos

Caso deseje instalar o projeto para verificar, fique a vontade! Antes de começar, é necessário que os seguintes passos estejam configurados:

- Java 21 ou superior instalado no ambiente;
- Maven instalado e apontado nas variáveis de ambiente;
- Geração da chave pública e privada através do OpenSSL (O projeto conta com uma chave pública e privada default, mas é de extrema necessidade que seja gerada uma referente ao seu ambiente);


## 🚀 Sobre o projeto

O projeto ainda está em desenvolvimento, não possuindo um frontend integrado com a aplicação, tendo que suas requisições sejam acessadas diretamente aos endpoints. Este projeto foi inicialmente criado para testar as habilidades em JWT e OAuth, alternativas de segurança na qual venho interagindo com elas a algum tempo. O projeto foi desenvolvido com Java 21, Spring como framework, JWT e OAuth como tecnologias de segurança de usuário, e, JPA e MySql como mapeamento de objetos e banco de dados. Foi desenvolvido alguns passos com o SSL para geração da chave pública e privada necessárias ao JWT, mas que fogem do escopo deste projeto. 


## :coffee: Contratos da API
O projeto conta com as funcionalidades detalhadas em seu descritivo e as suas necessidades antes do deploy em sua máquina pessoal já mencionadas. Com estes 2 pontos configurados, já é possível subir o projeto e realizar um teste com sua integração verificando os constratos passados no swagger.yaml.

Em caso de dúvida ou alterações, estou a disposição. Abraços! 👋
