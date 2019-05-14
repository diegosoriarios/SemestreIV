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
    
    criaAresta(g, 1, 2, 0);
    criaAresta(g, 2, 0, 0);
    criaAresta(g, 3, 4, 0);
    criaAresta(g, 4, 1, 0);
    criaAresta(g, 5, 3, 0);
    criaAresta(g, 3, 2, 0);
    /*mostraGrafo(g);
    */

    while(opt != 0) {
        //UNIX
        //system("clear");

        cout << "0 - Sair\n"
             << "1 - Adicionar Vertice\n"
             << "2 - Criar aresta\n"
             << "3 - Mostrar grafo\n"
             << "4 - Remover vertice\n"
             << "5 - Remover aresta\n"
             << "6 - Buscar vertice\n";
        cout << "Selecione uma opção: ";
        cin >> opt;
        switch(opt) {
            case 0:
                return 0;
            case 1:
                g = adicionarVertice(g);
                break;
            case 2:
                cout << "Selecione o vertice inicial: ";
                cin >> vi;
                cout << "Selecione o vertice final: ";
                cin >> vf;
                cout << "Digite o peso da aresta ";
                cout << "(0 se caso a aresta não seja ponderada): ";
                cin >> peso;
                criaAresta(g, vi, vf, peso);
                break;
            case 3:
                mostraGrafo(g);
                break;
            case 4:
                int valor;
                cout << "Digite o vertice a ser removido: ";
                cin >> valor;
                g = removerVertice(g, valor);
                break;
            case 5:
                cout << "Selecione o vertice inicial: ";
                cin >> vi;
                cout << "Selecione o vertice final: ";
                cin >> vf;
                //removeAresta(g, vi, vf);
                break;
            /*
            case 6:
                int valor;
                cout << "Digite o vertice a ser buscado: ";
                cin >> valor;
                //int busca = profundidade(g, valor);
                Vertice busca = encontraVertice(g, valor);
                if(busca.dado != NULL) {
                    cout << "\nValor " << busca.dado << " encontrado\n";
                } else {
                    cout << "\nValor não encontrado\n";
                }
            */
        }
    }
}