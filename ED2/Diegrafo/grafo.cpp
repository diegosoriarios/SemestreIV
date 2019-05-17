/*
** g++ grafo.cpp -o grafo && ./grafo
*/

#include <cstdio>
#include <iostream>
#include <cstdlib>

using namespace std;

#include "grafo.hpp"

int main() {
    Grafo *g = grafoCreate(5);
    int opt = 1;
    int vi, vf, peso;

    criaAresta(g, 0, 2, 0);
    criaAresta(g, 1, 0, 0);
    criaAresta(g, 2, 4, 0);
    criaAresta(g, 3, 1, 0);
    criaAresta(g, 4, 3, 0);
    criaAresta(g, 3, 3, 0);
    criaAresta(g, 3, 2, 0);

    while(opt != 0) {

        cout << "0 - Sair\n"
             << "1 - Adicionar Vertice\n"
             << "2 - Criar aresta\n"
             << "3 - Mostrar grafo\n"
             << "4 - Remover vertice\n"
             << "5 - Remover aresta\n";
        cout << "Selecione uma opção: ";
        cin >> opt;
        switch(opt) {
            case 0:
                return 0;
            case 1:
                g = adicionarVertice(g);
                break;
            case 2:
                if(g->numVertice > 0) {
                    cout << "Selecione o vertice inicial: ";
                    cin >> vi;
                    cout << "Selecione o vertice final: ";
                    cin >> vf;
                    cout << "Digite o peso da aresta ";
                    cout << "(0 se caso a aresta não seja ponderada): ";
                    cin >> peso;
                    criaAresta(g, vi, vf, peso);
                } else {
                    cout << "Adicione um vertice primeiro\n";
                }
                break;
            case 3:
                if(g->numVertice > 0) {
                    mostraGrafo(g);
                } else {
                    cout << "Grafo está vazio, adicione um vertice\n";
                }
                break;
            case 4:
                if(g->numVertice > 0) {
                    int valor;
                    cout << "Digite o vertice a ser removido: ";
                    cin >> valor;
                    removerVertice(g, valor);
                } else {
                    cout << "Grafo está vazio, adicione um vertice\n";
                }
                break;
            case 5:
                if(g->numVertice > 0 || g->numArestas > 0) {
                    cout << "Selecione o vertice inicial: ";
                    cin >> vi;
                    cout << "Selecione o vertice final: ";
                    cin >> vf;
                    removeAresta(g, vi, vf);
                } else {
                    cout << "Grafo está vazio, adicione um vertice\n";
                }
                break;
        }
    }
}
