#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include "4.hpp"

using namespace std;

main()
{
    int menu = -1, num, cont = 0;
    string titulo;
    arvore *a = cria_arvore();
    item i;
    do
    {
        delete(&i);
        system("cls");
        cout << "MENU DE OPCOES" << endl;
        cout << " 0 - Sair" << endl;
        cout << " 1 - Incluir novo exemplar" << endl;
        cout << " 2 - Mostrar acervo" << endl;
        cout << " 3 - Consultar exemplar pelo ISBN" << endl;
        cout << " 4 - Consultar exemplar pelo titulo" << endl;
        cout << " 5 - Remover Livro" << endl;
        cout << "Sua escolha: ";
        cin >> menu;
        fflush(stdin);
        switch(menu)
        {
        case 0:
            system("cls");
            cout << "Programa finalizado";
            break;
        case 1:
            system("cls");
            cout << "Informe o codigo ISBN do livro: ";
            cin >> i.isbn;
            while(consulta(a, i.isbn))
            {
                system("cls");
                cout << "Ja existe um exemplar com este codigo!" << endl;
                cout << "Informe o codigo ISBN do livro: ";
                cin >> i.isbn;
            }
            fflush(stdin);
            cout << "Informe o nome do autor do livro: ";
            getline(cin, i.autor);
            cout << "Informe o nome do livro: ";
            getline(cin, i.titulo);
            cout << "Informe o nome da editora do livro: ";
            getline(cin, i.editora);
            cout << "Informe o ano do livro: ";
            cin >> i.ano;
            cout << "Informe o valor do livro: ";
            cin >> i.preco;
            inserir(&a, &i);
            cout << "LIVRO ADICIONADO COM SUCESSO!";
            fflush(stdin)
            getchar();
            break;

        case 2:
            system("cls");
            if(testa_vazia(a))
            {
                cout << "ARVORE VAZIA!";
            }
            else
            {
                cout << "ACERVO DE LIVROS: "<< endl;
                cout << "ISBN\t Titulo \t Autor \t Ano"<< endl;
                mostra(a);
            }

            getchar();
            break;
        case 3:
            system("cls");
            if(testa_vazia(a))
            {
                cout << "ARVORE VAZIA!";
            }
            else
            {
                cout << "Informe o codigo ISBN desejado: ";
                cin >> num;
                fflush(stdin);
                if(!consulta_isbn(a, num))
                    cout << "O exemplar nao foi encontrado.";
            }
            getchar();
            break;

        case 4:
            system("cls");
            if(testa_vazia(a))
            {
                cout << "ARVORE VAZIA!";
            }
            else
            {
                fflush(stdin);
                cout << "Informe o titulo desejado: ";
                getline(cin, titulo);
                if(!consulta_titulo(a, titulo))
                    cout << "O exemplar nao foi encontrado.";
            }
            getchar();
            break;

        case 5:
            system("cls");
            if(testa_vazia(a))
            {
                cout << "ARVORE VAZIA!";
            }
            else
            {
                cout << "Informe o codigo ISBN do livor a ser removido: ";
                cin >> num;
                fflush(stdin);
                if(a->sae == NULL && a->sad == NULL)
                {
                    delete (a);
                    a = NULL;
                }
                else
                    remover(&a, num);
                cout << "VALOR REMOVIDO COM SUCESSO.";
            }
            getchar();
            break;
        };

    }
    while(menu != 0);

}
