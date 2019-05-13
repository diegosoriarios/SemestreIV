#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

#include "arvoreBinaria.hpp"

main(){
    int menu, num;
    ARVORE *t = cria_arvore();
    do {
        cout << "0 - Sair\n"
             << "1 - Incluir\n"
             << "2 - Mostrar\n"
             << "3 - Excluir\n";
        cin >> menu;
        fflush(stdin);
        switch(menu){
            case 0:
                system("clear");
                cout << "Programa finalizado";
                cout << endl;
                return 0;
            case 1:
                system("clear");
                cout << "Informe o valor a ser excluido: ";
                cin >> num;
                fflush(stdin);
                inserir(&t, num);
                cout << "Valor Inserido com Sucesso\n";
                cout << endl;
                break;
            case 2:
                system("clear");
                if(treeIsEmpty(t)){
                    cout << "Arvore está vazia";
                }else{
                    cout << "Elementos em pré-ordem: ";
                    mostraArvorePreOrdem(t);
                }
                cout << endl;
                cout << endl;
                break;
            case 3:
                system("clear");
                if(treeIsEmpty(t)){
                    cout << "Arvore Vazia";
                }else{
                    cout << "Informe o valor a ser removido: ";
                    cin >> num;
                    fflush(stdin);
                    remover(&t, num);
                    cout << "Valor Removido\n";
                }
                cout << endl;
                break;
            default:
                system("clear");
                cout << "Digite uma opção correta";
                cout << endl;
                break;
        };
    }while(menu != 0);
}

