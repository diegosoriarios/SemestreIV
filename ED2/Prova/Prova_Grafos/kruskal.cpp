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
    g.kruskal(0);



    g.toString();
    /* Outputs
       0: 0 1 1 0
       1: 1 0 1 0
       2: 1 1 0 1
       3: 0 0 1 0
      */

}
