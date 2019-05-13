#include <iostream>
using namespace std;

class Graph {
private:
      bool** adjMatrix;
      int numVertices;
public:
      Graph(int numVertices) {
            this->numVertices = numVertices;
            adjMatrix = new bool*[numVertices];
            for (int i = 0; i < numVertices; i++) {
                  adjMatrix[i] = new bool[numVertices];
                  for (int j = 0; j < numVertices; j++)
                        adjMatrix[i][j] = false;
          }
    }

      void addEdge(int i, int j) {
                  adjMatrix[i][j] = true;
                  adjMatrix[j][i] = true;
    }

      void removeEdge(int i, int j) {
                  adjMatrix[i][j] = false;
                  adjMatrix[j][i] = false;
    }

      bool isEdge(int i, int j) {
                  return adjMatrix[i][j];
    }

    void toString() {
      for (int i = 0; i < numVertices; i++) {
                  cout << i << " : ";
                  for (int j = 0; j < numVertices; j++)
                        cout << adjMatrix[i][j] << " ";
                  cout << "\n";
      }

    }

    ~Graph() { //destrutor de classe\A
            for (int i = 0; i < numVertices; i++)
                  delete[] adjMatrix[i];
            delete[] adjMatrix;
    }

    void kruscal(Graph *gr, int orig, int *pai) {
        int i, j, dest, NV, primeiro, *arv;
        double menorPeso;
        NV = gr->nro_vertices;
        arv = (int*) malloc(NV * sizeof(int));
        for( i = 0; i < NV; i++) {
            arv[i] = i;
            pai[i] = -1
        }
        pai[orig] = orig;
        while(1) {
            primeiro = 1;
            for(i = 0; i < NV; i++) {
                for(j = 0; i < gr->grau[i]; j++) {
                    if(arv[i] != arv[gr->arestas[i][j]]) {
                        if(primeiro) {
                            menorPeso = gr->pesos[i][j];
                            orig = i;
                            dest = gr->arestas[i][j];
                            primeiro = 0
                        } else if(menorPeso > gr->pesos[i][j]) {
                            menorPeso = gr->pesos[i][j];
                            orig = i;
                            dest = gr->arestas[i][j];
                        }
                    }
                }
            }
            if(primeiro == 1)
            break;
            if(pai[orig] == -1)
                pai[orig] = dest;
            else
                pai[dest] = orig;

            for(i = 0; i < NV; i++)
                if(arv[i] == arv[dest])
                    arv[i] = arv[orig];
        }
        free(arv);
    }
};


int main(){
        Graph g(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(4, 4);
        g.addEdge(5, 1);

        g.toString();

}
