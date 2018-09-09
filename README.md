# Versão
Este ramo contém a versão mais básica e simples da aplicação.
# Utilização do Git

- Instalar Git
`sudo apt-get install update`
`sudo apt-get install git`

- Configurar Git
`git config --global user.name "Seu Nome"`
`git config --global user.email email@example.com`

- Iniciar repositório
`cd ~/sua-pasta/`
`git clone https://github.com/Mury12/mqtt`
 
- Criar ramo de desenvolvimento
`git checkout -b dev`

- Atualizar ramo de desenvolvimento
`git pull origin dev`

# Fluxo de Trabalho
Ao utilizar um repositório não utilize o ramo principal para desenvolver sua atividade, ao invés disso, crie um novo ramo a partir do ramo principal
`git checkout dev`
`git checkout -b seu-ramo`
A partir desse momento você está apto para modificar o código sem interferir ou prejudicar o ramo em funcionamento. Ao criar um ramo para sua atividade:
- Vá na aba de projetos neste repositório, clique no cartão de sua atividade, clique em "Assign yourself" para marcar você mesmo no cartão caso não esteja marcado, dessa forma, todos poderão saber quem está trabalhando em qual atividade.
- Mova seu cartão para a coluna "Doing".
- Ao terminar sua atividade:
`git add -A`
`git commit -m "sua mensagem"`
`git push origin nome-do-seu-ramo`
- Vá para o site do github, clique em New pull request, escolha o ramo Dev no primeiro campo e seu ramo no outro, referencie seu pull request para o cartão da atividade na qual foi desenvolvida digitando # e o numero do cartão (que fica na parte de cima), por exemplo #1.
- Mova seu cartão para a coluna Review e aguarde algum dos colaboradores aprovarem sua parte e mesclarem com o ramo principal.
- Retorne para o ramo principal e atualize seu repositório.
`git checkout dev`
`git pull origin dev`
