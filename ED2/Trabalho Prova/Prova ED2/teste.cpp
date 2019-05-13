#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#define true 1
#define false 0

typedef int bool;
typedef int TIPOPESO;

#define BRANCO 0
#define CINZA 1
#define PRETO 2
using namespace std;

struct AdjListNode {
    int dest;
    TIPOPESO peso;
    struct AdjListNode *next;
} ADJACENCIA;

struct AdjList {

    struct AdjListNode *head;
} VERTICE;

struct grafo {
    int vertices;
    int arestas;
    VERTICE *adj
} GRAFO;



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
};

int main(){

    Graph g(5);
    g.newEdge(0,4);
    g.newEdge(0,1);
    g.newEdge(2,3);
    g.newEdge(3,4);
    g.newEdge(1,2);
    g.newEdge(1,3);
    g.newEdge(1,4);


    g.printGraph();

    return 0;
}

