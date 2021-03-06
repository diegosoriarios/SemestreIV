typedef struct adjacencia {
    int vertice;
    int peso;
    struct adjacencia *prox;
} Adj;

typedef struct vertice {
    int dado;
    Adj *cab;
} Vertice;

typedef struct grafo {
    int numVertice;
    int numArestas;
    Vertice *adj;
} Grafo;

Adj* criaAdj(int v, int peso) {
    if(v >= 0) {
        Adj *newAdj = (Adj*)malloc(sizeof(Adj));
        newAdj->vertice = v;
        newAdj->peso = peso;
        newAdj->prox = NULL;
        return newAdj;
    } else {
        return NULL;
    }
}

int criaAresta(Grafo *g, int vi, int vf, int peso) {
    if(g == NULL) {
        return 0;
    }
    if((vf < 0) || (vf >= g->numVertice)) {
        return 0;
    }
    if((vi < 0) || (vi >= g->numVertice)) {
        return 0;
    }
    Adj *newAdj = criaAdj(vf, peso);
    newAdj->prox = g->adj[vi].cab;
    g->adj[vi].cab = newAdj;
    g->numArestas++;
    return 1;
}

Grafo *grafoCreate(int v) {
    if(v > 0) {
        Grafo *g = (Grafo*)malloc(sizeof(Grafo));
        g->numVertice = v;
        g->numArestas = 0;
        g->adj = (Vertice*)malloc(sizeof(Vertice)*v);

        for(int i = 0; i < v; i++) {
            g->adj[i].cab = NULL;
            g->adj[i].dado = i;
        }
        return g;
    } else {
        return NULL;
    }
}
void mostraGrafo(Grafo *g) {
    if(g != NULL) {
        cout << "Informações do grafo:\nNumero de vertices: "
             << g->numVertice << "\nNumero de arestas: "
             << g->numArestas << "\nAdjacencia entre os vertices\n";
        for(int i = 0; i < g->numVertice; i++) {
            cout << "V" << i << ": ";
            Adj* aux = g->adj[i].cab;
            while(aux) {
                cout << "v" << aux->vertice << "(" << aux->peso << ")\t";
                aux = aux->prox;
            }
            cout << "\n";
        }
    }
}

int visita(Grafo *g, int i, int *cor, int key) {
    cor[i] = 1;
    int flag;

    Adj *v = g->adj[i].cab;
    /*
    if(g->adj[i].cab) {
        cout << "X";
    }
    */
    if(g->adj[i].dado == key) {
        return 1;
    }
    while(v) {
        if(cor[v->vertice] == 0) {
            flag = visita(g, v->vertice, cor, key);
            if(flag) {
                return 1;
            }
        }
        v = v->prox;
    }
    cor[i] = 2;
    return 0;
}

int profundidade(Grafo *g, int key) {
    int flag;
    int cor[g->numVertice];
    for(int i = 0; i < g->numVertice; i++) {
        cor[i] = 0;
    }
    for(int i = 0; i < g->numVertice; i++) {
        if(cor[i] == 0) {
            flag = visita(g, i, cor, key);
            if(flag) {
                return 1;
            }
        }
    }
    return 0;
}

Grafo *adicionarVertice(Grafo *g) {
    int novoTamanho = g->numVertice + 1;
    g->adj = (Vertice*)realloc(g->adj, novoTamanho * sizeof(Vertice));
    g->numVertice = novoTamanho;
    g->adj[novoTamanho-1].dado = novoTamanho;
    g->adj[novoTamanho-1].cab = NULL;
    return g;

}

void removerVertice(Grafo *g, int valor) {
    if(g->adj[valor].cab == NULL) {
        int novoTamanho = g->numVertice - 1;
        Vertice *aux = (Vertice*)malloc(sizeof(Vertice)*novoTamanho);
        int i = 0;
        int a = 0;
        while(a < novoTamanho) {
            if(g->adj[a].dado == valor) {
                i++;
            }
            aux[a] = g->adj[i];
            a++;
            i++;
        }
        g->adj = aux;
        g->numVertice = novoTamanho;
        //free(aux);
    } else {
        cout << "Precisa remover as arestas\n";
    }
}

Adj* buscaRecursiva(Adj *aresta, int valor){
    if (!aresta) {
        return NULL;
    }else if (aresta->vertice == valor){
        return aresta;
    }else{
        return buscaRecursiva(aresta->prox, valor);
    }
}

void removeAresta(Grafo *g, int vi, int vf) {
    Adj* aux = g->adj[vi].cab;
    Adj *add = aux;
    int i = 0;
    cout << aux << "\t";
    while(aux) {
        i++;
        if(aux->prox == NULL){
            break;
        }
        aux = aux->prox;
        cout << aux << "\t";
    }
    cout << "\n";
    aux = add;
    int tam = i-1;
    int vertices[tam], peso[tam];
    i = 0;
    cout << "Asdas" << aux << "\t";
    while(aux){
        if(aux->vertice != vf) {
            vertices[i] = aux->vertice;
            peso[i] = aux->peso;
            i++;
        }
        g->numArestas--;
        aux = aux->prox;
        cout << i;
    }
    g->adj[vi].cab = NULL;
    for(i = 0; i < tam; i++) {
        criaAresta(g, vi, vertices[i], peso[i]);
    }
}

//LINUX
//g++ grafo.cpp -o grafo && ./grafo
