// frontend/app.js

async function carregarProdutos() {
    try {
        const res = await fetch('http://localhost:8080/produtos');
        const produtos = await res.json();

        const container = document.getElementById('produtos');
        container.innerHTML = "";

        produtos.forEach(p => {
            const div = document.createElement('div');
            div.className = 'produto';
            div.innerHTML = `
                <strong>${p.name}</strong> - R$${p.price} 
                <button onclick="adicionarAoCarrinho(${p.id})">Adicionar</button>
            `;
            container.appendChild(div);
        });
    } catch (err) {
        console.error("Erro ao carregar produtos", err);
    }
}

async function carregarCarrinho(clienteId = 1) {
    try {
        const res = await fetch(`http://localhost:8080/carrinho/${clienteId}`);
        if (!res.ok) {
            console.log("Carrinho não encontrado");
            return;
        }

        const carrinho = await res.json();
        const container = document.getElementById('carrinho');
        container.innerHTML = "";

        if (!carrinho.itens || carrinho.itens.length === 0) {
            container.textContent = "Carrinho vazio";
            return;
        }

        let total = 0;

        carrinho.itens.forEach(item => {
            const div = document.createElement('div');
            div.className = 'pedido';

            div.textContent = `${item.produto.name} x${item.quantidade}`;
            total += item.quantidade * item.produto.price;

            container.appendChild(div);
        });

        const totalDiv = document.createElement('p');
        totalDiv.textContent = `Total: R$${total.toFixed(2)}`;
        container.appendChild(totalDiv);

    } catch (err) {
        console.error("Erro ao carregar carrinho", err);
    }
}

async function adicionarAoCarrinho(produtoId, clienteId = 1) {
    try {
        const res = await fetch(`http://localhost:8080/carrinho/${clienteId}/produto/${produtoId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({}) // só precisa enviar produtoId pela URL
        });

        if (res.ok) {
            alert("Produto adicionado ao carrinho!");
            carregarCarrinho(clienteId);
        } else {
            alert("Erro ao adicionar ao carrinho.");
        }
    } catch (err) {
        console.error("Erro na requisição", err);
    }
}

// Carrega tudo ao iniciar
carregarProdutos();
carregarCarrinho();