# %%
import requests
import json

# %%
BASE_URL = "http://192.168.90.90:8080"

def criar_produto(nome_produto, preco, categoria):
    url = BASE_URL + "/produtos/criar-produto"
    produto = {
        "nomeProduto": nome_produto,
        "preco": preco,
        "categoria": categoria
    }
    response = requests.post(url, json=produto)
    print("Criar Produto:", response.status_code)
    print(response.json())
    return response.json()

def buscar_produto(id):
    url = f"{BASE_URL}/produtos/listar-produtos/{id}"
    response = requests.get(url)
    print("Buscar Produto:", response.status_code)
    if response.status_code == 200:
        print(response.json())
    else:
        print(response.text)

def atualizar_produto(id,nome_produto, preco, categoria):
    url = f"{BASE_URL}/produtos/atualizar-produto/{id}"
    produto_atualizado = {
        "nomeProduto": nome_produto,
        "preco": preco,
        "categoria": categoria
    }
    response = requests.put(url, json=produto_atualizado)
    print("Atualizar Produto:", response.status_code)

def deletar_produto(id):
    url = f"{BASE_URL}/produtos/deletar-produto/{id}"
    response = requests.delete(url)
    print("Deletar Produto:", response.status_code)



# %%
def criar_funcionario(nome, cargo, salario):
    url = f"{BASE_URL}/funcionarios/criar"
    funcionario = {
        "nome": nome,
        "cargo": cargo,
        "salario": salario
    }
    response = requests.post(url, json=funcionario)
    print("Criar Funcionário:", response.status_code)
    print(response.json())
    return response.json()

def buscar_funcionario(id):
    url = f"{BASE_URL}/funcionarios/{id}"
    response = requests.get(url)
    print("Buscar Funcionário:", response.status_code)
    print(response.json())

def atualizar_funcionario(id, nome, cargo, salario):
    url = f"{BASE_URL}/funcionarios/atualizar/{id}"
    funcionario_atualizado = {
        "nome": nome,
        "cargo": cargo,
        "salario": salario
    }
    response = requests.put(url, json=funcionario_atualizado)
    print("Atualizar Funcionário:", response.status_code)

def deletar_funcionario(id):
    url = f"{BASE_URL}/funcionarios/deletar/{id}"
    response = requests.delete(url)
    print("Deletar Funcionário:", response.status_code)


# %%
def criar_cliente(nome, data_nascimento, cpf):
    url = f"{BASE_URL}/clientes/criar"
    cliente = {
        "nome": nome,
        "dataNascimento": data_nascimento,
        "cpf": cpf
    }
    response = requests.post(url, json=cliente)
    print("Criar Cliente:", response.status_code)
    print(response.json())
    return response.json()

def buscar_cliente(id):
    url = f"{BASE_URL}/clientes/{id}"
    response = requests.get(url)
    print("Buscar Cliente:", response.status_code)
    print(response.json())

def atualizar_cliente(id, nome, data_nascimento, cpf):
    url = f"{BASE_URL}/clientes/atualizar/{id}"
    cliente_atualizado = {
        "nome": nome,
        "dataNascimento": data_nascimento,
        "cpf": cpf
    }
    response = requests.put(url, json=cliente_atualizado)
    print("Atualizar Cliente:", response.status_code)

def deletar_cliente(id):
    url = f"{BASE_URL}/clientes/deletar/{id}"
    response = requests.delete(url)
    print("Deletar Cliente:", response.status_code)

# %%
def criar_moto(placa, modelo, cilindrada):
    dados = {
        "placa": placa,
        "modelo": modelo,
        "cilindrada": cilindrada
    }
    resposta = requests.post(f"{BASE_URL}/motos/criar", json=dados)
    print("Moto criada:", resposta.json())
    return resposta.json()

def buscar_moto(id_moto):
    resposta = requests.get(f"{BASE_URL}/motos/{id_moto}")
    if resposta.status_code == 200:
        print("Moto encontrada:", resposta.json())
    else:
        print("Moto não encontrada.")

def listar_motos():
    resposta = requests.get(f"{BASE_URL}/motos/listar")
    print("Lista de motos:")
    for moto in resposta.json():
        print(moto)

def atualizar_moto(id_moto, placa, modelo, cilindrada):
    dados = {
        "placa": placa,
        "modelo": modelo,
        "cilindrada": cilindrada
    }
    resposta = requests.put(f"{BASE_URL}/motos/atualizar/{id_moto}", json=dados)
    if resposta.status_code == 204:
        print("Moto atualizada com sucesso.")
    else:
        print("Falha ao atualizar moto.")

def deletar_moto(id_moto):
    resposta = requests.delete(f"{BASE_URL}/motos/deletar/{id_moto}")
    if resposta.status_code == 204:
        print("Moto deletada com sucesso.")
    else:
        print("Falha ao deletar moto.")


# %%
def criar_carro(placa, modelo, quantidade_de_portas):
    dados = {
        "placa": placa,
        "modelo": modelo,
        "quantidade_de_portas": quantidade_de_portas
    }
    resposta = requests.post(f"{BASE_URL}/carros/criar", json=dados)
    print("carro criada:", resposta.json())
    return resposta.json()

def buscar_carro(id_carro):
    resposta = requests.get(f"{BASE_URL}/carros/{id_carro}")
    if resposta.status_code == 200:
        print("carro encontrada:", resposta.json())
    else:
        print("carro não encontrada.")

def listar_carros():
    resposta = requests.get(f"{BASE_URL}/carros/listar")
    print("Lista de carros:")
    for carro in resposta.json():
        print(carro)

def atualizar_carro(id_carro, placa, modelo, quantidade_de_portas):
    dados = {
        "placa": placa,
        "modelo": modelo,
        "quantidade_de_portas": quantidade_de_portas
    }
    resposta = requests.put(f"{BASE_URL}/carros/atualizar/{id_carro}", json=dados)
    if resposta.status_code == 204:
        print("carro atualizada com sucesso.")
    else:
        print("Falha ao atualizar carro.")

def deletar_carro(id_carro):
    resposta = requests.delete(f"{BASE_URL}/carros/deletar/{id_carro}")
    if resposta.status_code == 204:
        print("carro deletada com sucesso.")
    else:
        print("Falha ao deletar carro.")


# %%
from datetime import date

def criar_pedido(codigo, cliente_id, entregador_id):
    dados = {
        "codigoPedido": codigo,
        "dataPedido": date.today().isoformat(),
        "cliente": {"id": cliente_id},
        "entregador": {"id": entregador_id}
    }
    resposta = requests.post(f"{BASE_URL}/pedidos/criar", json=dados)
    print("Pedido criado:", resposta.json())
    return resposta.json()

def atualizar_pedido(id_pedido, codigo, cliente_id, entregador_id):
    dados = {
        "codigoPedido": codigo,
        "dataPedido": date.today().isoformat(),
        "cliente": {"id": cliente_id},
        "entregador": {"id": entregador_id}
    }
    resposta = requests.put(f"{BASE_URL}/pedidos/atualizar/{id_pedido}", json=dados)
    if resposta.status_code == 204:
        print("Pedido atualizado com sucesso.")
    else:
        print("Falha ao atualizar pedido.")


def buscar_pedido(id_pedido):
    resposta = requests.get(f"{BASE_URL}/pedidos/{id_pedido}")
    if resposta.status_code == 200:
        print("Pedido encontrado:", resposta.json())
    else:
        print("Pedido não encontrado.")

def listar_pedidos():
    resposta = requests.get(f"{BASE_URL}/pedidos/listar")
    print("Lista de pedidos:")
    for pedido in resposta.json():
        print(pedido)


def deletar_pedido(id_pedido):
    resposta = requests.delete(f"{BASE_URL}/pedidos/deletar/{id_pedido}")
    if resposta.status_code == 204:
        print("Pedido deletado com sucesso.")
    else:
        print("Falha ao deletar pedido.")


# %%
def criar_item_pedido(pedido_id, produto_id, quantidade, preco_unitario):
    dados = {
        "pedido": {"id": pedido_id},
        "produto": {"id": produto_id},
        "quantidade": quantidade,
        "precoUnitario": preco_unitario
    }
    resposta = requests.post(f"{BASE_URL}/itens-pedido/criar", json=dados)
    print("Item criado:", resposta.json())
    return resposta.json()

def buscar_item_pedido(id_item):
    resposta = requests.get(f"{BASE_URL}/itens-pedido/{id_item}")
    if resposta.status_code == 200:
        print("Item encontrado:", resposta.json())
    else:
        print("Item não encontrado.")

def listar_itens_pedido():
    resposta = requests.get(f"{BASE_URL}/itens-pedido/listar")
    print("Lista de itens do pedido:")
    for item in resposta.json():
        print(item)

def atualizar_item_pedido(id_item, pedido_id, produto_id, quantidade, preco_unitario):
    dados = {
        "pedido": {"id": pedido_id},
        "produto": {"id": produto_id},
        "quantidade": quantidade,
        "precoUnitario": preco_unitario
    }
    resposta = requests.put(f"{BASE_URL}/itens-pedido/atualizar/{id_item}", json=dados)
    if resposta.status_code == 204:
        print("Item atualizado com sucesso.")
    else:
        print("Falha ao atualizar item.")

def deletar_item_pedido(id_item):
    resposta = requests.delete(f"{BASE_URL}/itens-pedido/deletar/{id_item}")
    if resposta.status_code == 204:
        print("Item deletado com sucesso.")
    else:
        print("Falha ao deletar item.")


# %%
if __name__ == "__main__":
    #Produto
    print("--------------------------")
    produto = criar_produto("Celular", 1000, "Celulares")
    produto2 = criar_produto("Celular2", 2000, "Celulares")
    produto3 = criar_produto("Capinha", 20, "Celulares")
    buscar_produto(produto['id'])
    atualizar_produto(produto['id'], "Celular", 900, "Celulares")
    atualizar_produto(produto3['id'], "Capinha", 20, "Utensílios")
    buscar_produto(produto['id'])
    buscar_produto(produto['id']) 
    deletar_produto(produto2['id'])

     #funcionário
    print("--------------------------")
    f = criar_funcionario("Neto", "ti", 1500)
    buscar_funcionario(f['id'])
    atualizar_funcionario(f['id'], "Neto", "uber", 3000)
    buscar_funcionario(f['id'])
    #deletar_funcionario(f['id'])

    #cliente
    print("--------------------------")
    c = criar_cliente("Felipe", "2002-05-05", "111.999.888-55")
    buscar_cliente(c['id'])
    atualizar_cliente(c['id'], "Felipe", "2002-05-05", "123.999.888-55")
    buscar_cliente(c['id'])
    #deletar_cliente(c['id'])

    #moto
    print("--------------------------")
    nova_moto = criar_moto("ABd1234", "Honda CG 160", 160)
    buscar_moto(nova_moto["id"])
    listar_motos()
    atualizar_moto(nova_moto["id"], "XYZ4321", "Yamaha Fazer", 250)
    listar_motos()
    #deletar_moto(nova_moto["id"])


    #carro
    print("--------------------------")
    nova_carro = criar_carro("ABC1222", "Fiat Uno", 2)
    buscar_carro(nova_carro["id"])
    listar_carros()
    atualizar_carro(nova_carro["id"], "XYZ4321", "Fiat Uno", 2)
    listar_carros()
    #deletar_carro(nova_carro["id"])

        # pedido
    print("--------------------------")
    pedido = criar_pedido("PED123", c["id"], f["id"])
    buscar_pedido(pedido["id"])
    listar_pedidos()
    atualizar_pedido(pedido["id"], "PED456", c["id"], f["id"])
    buscar_pedido(pedido["id"])
    #deletar_pedido(pedido["id"])
    buscar_pedido(pedido["id"])

        # itens do pedido
    print("--------------------------")
    item = criar_item_pedido(pedido["id"], produto["id"], 1, 800)
    item2 = criar_item_pedido(pedido["id"], produto3["id"], 2, 19.90)
    buscar_item_pedido(item["id"])
    
    atualizar_item_pedido(item["id"], pedido["id"], produto["id"], 5, 18.50)
    buscar_item_pedido(item["id"])
    #deletar_item_pedido(item["id"])
    buscar_item_pedido(item["id"])
    listar_itens_pedido()



# %%



