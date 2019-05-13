#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

using namespace std;

typedef struct arv
{
    int info;
    arv *sae;
    arv *sad;
} arvore;

arvore* cria_arvore()
{
    return NULL;
}

bool testa_vazia(arvore *t)
{
    return t == NULL;
}

void mostra_preordem(arvore *t)
{
    cout << "<";
    if(!testa_vazia(t))
    {
        cout << t->info;
        mostra_preordem(t->sae);
        mostra_preordem(t->sad);
    }
    cout << ">";
}

void mostra_ordem(arvore *t)
{
    cout << "<";
    if(!testa_vazia(t))
    {
        mostra_ordem(t->sae);
        cout << t->info;
        mostra_ordem(t->sad);
    }
    cout << ">";
}

void mostra_posordem(arvore *t)
{
    cout << "<";
    if(!testa_vazia(t))
    {
        mostra_posordem(t->sae);
        mostra_posordem(t->sad);
        cout << t->info;
    }
    cout << ">";
}

void inserir(arvore **t, int v)
{
    //arvore esta vazia
    if(*t == NULL)
    {
        *t = new arvore;
        (*t)->sae = NULL;
        (*t)->sad = NULL;
        (*t)->info = v;
    }
    else
    {
        if(v < (*t)->info)
            inserir(&(*t)->sae, v);
        else
            inserir(&(*t)->sad, v);
    }
}

bool consultar(arvore *t, int valor)
{
    if(!testa_vazia(t))
    {
        if(t->info == valor)
            return true;
        if(consultar(t->sae, valor))
            return true;
        if(consultar(t->sad, valor))
            return true;
    }
    return false;
}

main()
{
    int menu, valor;
    arvore *a = cria_arvore();

    do
    {
        system("cls");
        cout << "MANU DE OPCOES" << endl;
        cout << "0 - Sair" << endl;
        cout << "1 - Incluir" << endl;
        cout << "2 - Mostrar pre-ordem" << endl;
        cout << "3 - Consultar" << endl;
        cout << "4 - Excluir" << endl;
        cout << "5 - Mostrar in-ordem" << endl;
        cout << "6 - Mostrar pos-ordem" << endl;
        cout << "Sua escolha: ";
        cin >> menu;
        fflush(stdin);

        switch (menu)
        {
        case 0:
            system("cls");
            cout << "Programa finalizado";
            break;

        case 1:
            system("cls");
            cout << "Informe um valor a ser inserido: ";
            cin >> valor;
            inserir(&a, valor);
            cout << "Valor inserido com sucesso!";
            fflush(stdin);
            getchar();
            break;

        case 2:
            system("cls");
            if(testa_vazia(a))
                cout << "A arvore esta vazia!";
            else
            {
                cout << "ELEMENTOS EM PRE-ORDEM: " << endl;
                mostra_preordem(a);
            }
            getchar();
            break;

        case 3:
            system("cls");
            cout << "Informe um valor: ";
            cin >> valor;
            fflush(stdin);
            if(testa_vazia(a))
                cout << "A arvore esta vazia!";
            else
            {
                if(consultar(a, valor))
                    cout << "\nO valor foi encontrado!";
                else
                    cout << "\nO valor nao foi encontrado!";
            }
            getchar();
            break;

        case 4:
            system("cls");
            cout << "Programa finalizado";
            getchar();
            break;

        case 5:
            system("cls");
            if(testa_vazia(a))
                cout << "A arvore esta vazia!";
            else
            {
                cout << "ELEMENTOS EM IN-ORDEM: " << endl;
                mostra_ordem(a);
            }
            getchar();
            break;

        case 6:
            system("cls");
            if(testa_vazia(a))
                cout << "A arvore esta vazia!";
            else
            {
                cout << "ELEMENTOS EM POS-ORDEM: " << endl;
                mostra_posordem(a);
            }
            getchar();
            break;

        };

    }
    while(menu != 0);

}
