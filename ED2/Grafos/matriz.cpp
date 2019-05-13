#include <iostream>
#include <stdlib.h>
#include <stdio.h>

using namespace std;

struct AdjListNode {
    int dest;
    struct AdjListNode *next;
};

struct AdjList {
    struct AdjListNode *head;
};

class Graph {
private:
    int V; //numero de vertices
    struct AdjList *lista;

public:
    Graph(int V){
        this->V = V;
        lista = new AdjList[V]; //define array do tipo AdjList com o numero de vertices
        for(int i = 0; i < V; i++){
            lista[i].head = NULL;
        }
    }

    AdjListNode* newNode(int dest){
        AdjListNode* novo = new AdjListNode;
        novo->dest = dest;
        novo->next = NULL;
        return novo;
    }

    void newEdge(int src, int dest){
        //origem para destino
        AdjListNode *novo = newNode(dest);
        novo->next = lista[src].head;
        lista[src].head = novo;
        //destino para origem
        novo = newNode(src);
        novo->next = lista[dest].head;
        lista[dest].head = novo;
    }

    /*
        bool removerUsuario(int ID){

    Usuario *anterior = NULL;
    Usuario *atual = lu->inicio;

    while(atual && atual->ID != ID)
    {
        anterior = atual;
        atual = atual->prox;
    }

    if(!atual){ //nao achou o no?
        cout << "Erro ao imprimir amigos do usuario " << ID << ". O usuario nao existe." << endl;
        return false;
    }

    while(atual->amigos->inicio){
        removerAmigo(atual->ID, atual->amigos->inicio->usr->ID);
    }

    if(!anterior)  //O nó é o primeiro da lista ?
    {
        lu->inicio = atual->prox;
        if(!lu->inicio)
            lu->fim = lu->inicio;
    }
    else  //O nó está no meio/final da lista ?
    {
        anterior->prox = atual->prox;

        if(!atual->prox)
            lu->fim = anterior;
    }

    lu->tamanho--;
    delete(atual);
    delete(anterior);
    return true;
}
    */
    bool delNode(int src, int dest){
        AdjListNode* nodo = lista[src].head;
        AdjListNode* aux = NULL;
        while(nodo && nodo->dest != dest){
            aux = nodo;
            nodo = nodo->next;
        }
        if(!nodo)
            return false;
        if(!aux)
            lista[src].head = nodo->next;
        else
            aux->next = nodo->next;
        delete(nodo);
        return true;
    }

    void delEdge(int src, int dest){
        bool result = delNode(src, dest);
        delNode(dest,src);
        if(result)
            cout << "\n Aresta excluida: {" << src << ", " << dest << "}";
    }

    void printGraph(){
        int v;
        for(v = 0; v < V; v++){
            AdjListNode *nodo = lista[v].head;
            cout << "\n Lista de adjacência do vértice " << v << endl;
            while(nodo){
                cout << "-> " << nodo->dest;
                nodo = nodo->next;
            }
            cout << endl;
        }
    }

    void printGraph2(){
        int v, u;
        int mtz[V][V] = {0};
        cout << "Matriz de adjacência:" << endl << endl;
        for(v = 0; v < V; v++){
            cout << "\t" << v;
        }
        for(v = 0; v < V; v++){
            AdjListNode *nodo = lista[v].head;
            while(nodo){
                mtz[v][nodo->dest] = 1;
                nodo = nodo->next;
            }
            cout << endl << endl;
            cout << v << "\t";
            for(u = 0; u < V; u++){
                if(mtz[v][u] != 1)
                    mtz[v][u] = 0;
                cout << mtz[v][u] << "\t";
            }
        }

    }

};

int main(){
    setlocale(LC_ALL, "portuguese");
    Graph g(5);
    g.newEdge(0, 1);
    g.newEdge(0, 4);
    g.newEdge(1, 2);
    g.newEdge(1, 3);
    g.newEdge(1, 4);
    g.newEdge(2, 3);
    g.newEdge(3, 4);

    g.printGraph2();
    cout << endl << endl;
    g.delEdge(2, 3);
    cout << endl << endl;
    g.printGraph2();

    return 0;
}

