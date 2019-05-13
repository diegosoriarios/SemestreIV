#include <iostream>
using namespace std;

class Graph
{
private:
    int** adjMatrix;
    int *grau;
    int numVertices;
public:
    Graph(int numVertices)
    {
        this->numVertices = numVertices;
        adjMatrix = new int*[numVertices];
        grau = new int[numVertices];
        for (int i = 0; i < numVertices; i++)
        {
            adjMatrix[i] = new int[numVertices];
            grau[i] = 0;
            for (int j = 0; j < numVertices; j++)
                adjMatrix[i][j] = -1;
        }
    }

    void addEdge(int i, int j, int peso)
    {
        adjMatrix[i][j] = peso;
        adjMatrix[j][i] = peso;
        grau[i]++;
        grau[j]++;
    }

    void removeEdge(int i, int j)
    {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
        grau[i]--;
        grau[j]--;
    }

    bool isEdge(int i, int j)
    {
        return adjMatrix[i][j];
    }

    void toString()
    {
        for (int i = 0; i < numVertices; i++)
        {
            cout << i << " >  " << grau[i] << " : ";
            for (int j = 0; j < numVertices; j++)
                cout << adjMatrix[i][j] << " \t ";
            cout << "\n";
        }

    }

    void busca(int origem, int destino)
    {
        int vetIni = origem;
        int *pai;
        pai = new int[numVertices];
        int i,j,dest,primeiro,NV = numVertices;
        double menorPeso;
        int *arv = new int[numVertices];
        for(i=0; i<NV; i++)
        {
            arv[i]=i;
            pai[i]=-1;
        }
        pai[vetIni]=vetIni;
        while(1)
        {
            primeiro=1;
            for(i=0; i<NV; i++)
            {
                for(j=0; j<NV; j++)
                {
                    if (adjMatrix[i][j] != -1)
                    {
                        if(arv[i]!=arv[j])
                        {
                            if(primeiro)
                            {
                                menorPeso = adjMatrix[i][j];
                                vetIni=i;
                                dest=j;
                                primeiro=0;
                            }
                            else
                            {
                                if(menorPeso > adjMatrix[i][j])
                                {
                                    menorPeso = adjMatrix[i][j];
                                    vetIni=i;
                                    dest=j;
                                }
                            }
                        }
                    }
                }
            }
            if(primeiro==1)break;
            if(pai[vetIni]==-1)pai[vetIni]=dest;
            else pai[dest]=vetIni;

            for(i=0; i<NV; i++)
                if(arv[i]==arv[dest])
                    arv[i]=arv[vetIni];
        }
        int aux = destino;
        int path[NV];
        j = 0;
        int test = 0;
        for(i=0; i<NV; i++) {
            if (origem == i || destino == i)
                test++;
        }
        if(test < 2) {
            cout << "Caminho não encontrado!";
            return;
        }
        while(1) {
            for(i=0; i<NV; i++)
            {
                if(i == aux){
                    aux = pai[i];
                    path[j] = aux;
                    j++;
                }

            }
            if(aux == origem) {
                test = 1;
                break;
            }
        }
        if(test == 1) {
            cout << "Caminho encontrado!" << endl;
            if(path[j-1] == path[j-2]){
                for(j = j-2; j >= 0; j--)
                    cout << path[j] << " -> ";
            }
            else {
                for(j = j-1; j >= 0; j--)
                    cout << path[j] << " -> ";
            }
            cout << destino;
        }
    }

    void kruskal(int vetIni)
    {
        int *pai;
        pai = new int[numVertices];
        int i,j,dest,primeiro,NV = numVertices;
        double menorPeso;
        int *arv = new int[numVertices];
        for(i=0; i<NV; i++)
        {
            arv[i]=i;
            pai[i]=-1;
        }
        pai[vetIni]=vetIni;
        while(1)
        {
            primeiro=1;
            for(i=0; i<NV; i++)
            {
                for(j=0; j<NV; j++)
                {
                    if (adjMatrix[i][j] != -1)
                    {
                        if(arv[i]!=arv[j])
                        {
                            if(primeiro)
                            {
                                menorPeso = adjMatrix[i][j];
                                vetIni=i;
                                dest=j;
                                primeiro=0;
                            }
                            else
                            {
                                if(menorPeso > adjMatrix[i][j])
                                {
                                    menorPeso = adjMatrix[i][j];
                                    vetIni=i;
                                    dest=j;
                                }
                            }
                        }
                    }
                }
            }
            if(primeiro==1)break;
            if(pai[vetIni]==-1)pai[vetIni]=dest;
            else pai[dest]=vetIni;

            for(i=0; i<NV; i++)
                if(arv[i]==arv[dest])
                    arv[i]=arv[vetIni];
        }
        cout << "\n arvore \n";
        for(i=0; i<NV; i++)
        {
            cout << i << " : "  << pai[i] << " \t " << arv[i] << endl;

        }
    }

    ~Graph()   //destrutor de classe\A
    {
        for (int i = 0; i < numVertices; i++)
            delete[] adjMatrix[i];
        delete[] adjMatrix;
    }
};


int main()
{
    int origem, destino;
    Graph g(6);
    g.addEdge(0, 3, 5);
    g.addEdge(0, 1, 6);
    g.addEdge(0, 2, 1);
    g.addEdge(1, 2, 2);
    g.addEdge(1, 4, 5);
    g.addEdge(2, 3, 2);
    g.addEdge(2, 4, 6);
    g.addEdge(2, 5, 4);
    g.addEdge(3, 5, 4);
    g.addEdge(4, 5, 3);

    cout << "Informe o vertice de origem: ";
    cin >> origem;

    cout << "\nInforme o vertice de destino: ";
    cin >> destino;
    cout << endl;

    g.busca(origem, destino);

    cout << endl << endl;

    g.toString();

}
