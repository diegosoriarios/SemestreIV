#include <iostream>
#include <stdlib.h>

#define BRANCO 0
#define CINZA 1
#define PRETO 2

using namespace std;

struct AdjListNode {

    int dest;
    struct AdjListNode *next;
};

struct AdjList {

    struct AdjListNode *head;
};


class Graph{

private:
    int V; // numero de vertices
    struct AdjList *lista;
public:
    Graph(int V){
        this->V = V;
        // define array do tipo AdjList com o numero de vertices
        lista = new AdjList[V];
        for(int i=0; i< V; i++){
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
        //da origem para destino
        AdjListNode *novo = newNode(dest);
        novo->next = lista[src].head;
        lista[src].head = novo;
        //do destino para a origem
        novo = newNode(src);
        novo->next = lista[dest].head;
        lista[dest].head = novo;
    }

    void printGraph(){
        int v;
        for(v=0; v < V; v++){

            AdjListNode *nodo = lista[v].head;
            cout << "\n Lista de adjacencia do vertice " << v << endl;
            while(nodo){

                cout << "-> " << nodo->dest;
                nodo = nodo->next;
            }
            cout << endl;
        }
    }

    void profundidade(int src, int busca) {
        int cor[V], cont, test = 0;

        for(cont = 0; cont < V; cont++)
            cor[cont] = BRANCO;

        for(cont = src; cont < V; cont++){
            if(cor[cont] == BRANCO){
                visitaP(cont, cor, busca, test);
                if(test == 1) {
                    cout << "\n\nVertice encontrado!";
                    return;
                }
            }
        }
        cout << "\n\nVertice nao encontrado!";
    }

    void visitaP(int cont, int *cor, int busca, int &test) {
        cor[cont] = CINZA;
        AdjListNode *v = lista[cont].head;
        while (v){
            if(cor[v->dest] == BRANCO) {
                visitaP(v->dest, cor, busca, test);
            }
            if(v->dest == busca) {
                cout << v->dest;
                test = 1;
                return;
            }
            if(test == 1)return;
            cout << v->dest << " -> ";
            v = v->next;
        }
        cor[cont] = PRETO;
    }
};

int main(){

    int inicio, busca;
    Graph g(8);
    /*g.newEdge(0,4);
    g.newEdge(0,1);
    g.newEdge(2,3);
    g.newEdge(3,4);
    g.newEdge(1,2);
    g.newEdge(1,3);
    g.newEdge(1,4);*/
    g.newEdge(6, 7);
    g.newEdge(2, 6);
    g.newEdge(2, 5);
    g.newEdge(1, 4);
    g.newEdge(1, 3);
    g.newEdge(0, 2);
    g.newEdge(0, 1);

	cout << "Informe o vertice de inicio da busca: ";
	cin >> inicio;
	cout << endl << "Informe o vertice do que esta sendo procurado: ";
	cin >> busca;
    g.profundidade(inicio, busca);


    g.printGraph();

    return 0;
}

