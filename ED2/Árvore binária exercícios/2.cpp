#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

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

int testa_vazia(arvore *t)
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

void mostra_inordem(arvore *t)
{
    cout << "<";
    if(!testa_vazia(t))
    {
        mostra_inordem(t->sae);
        cout << t->info;
        mostra_inordem(t->sad);
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

bool consulta(arvore *t, int v)
{
    if(testa_vazia(t))
        return 0;
    return t->info == v || consulta(t->sae, v) || consulta(t->sad,v);
}

arvore* remover(arvore **t, int v)
{
    if(v < (*t)->info)
        remover(&(*t)->sae, v);
    else if(v > (*t)->info)
        remover(&(*t)->sad, v);
    else
    {
        //achou o elemento a ser removido
        arvore *aux = *t;
        if(((*t)->sae == NULL) && ((*t)->sad == NULL))
        {
            //é um nó folha
            delete(aux);
            (*t) = NULL;
        }
        else if((*t)->sae == NULL)
        {
            //só tem filho a direita
            (*t) = (*t)->sad;
            aux->sad = NULL;
            delete(aux);
            aux = NULL;
        }
        else if ((*t)->sad == NULL)
        {
            //só tem filho a esquerda
            (*t) = (*t)->sae;
            aux->sae = NULL;
            delete(aux);
            aux = NULL;
        }
        else
        {
            aux = (*t)->sae;
            while(aux->sad != NULL)
            {
                aux = aux->sad;
            }
            (*t)->info = aux->info;
            aux->info = v;
            (*t)->sae = remover(&(*t)->sae, v);
        }
    }
    return *t;
}

main()
{
    int menu = -1, num;
    arvore *par = cria_arvore();
    arvore *impar = cria_arvore();
    do
    {
        system("cls");
        cout << "MENU DE OPCOES" << endl;
        cout << " 0 - Sair" << endl;
        cout << " 1 - Incluir" << endl;
        cout << " 2 - Mostrar" << endl;
        cout << " 3 - Consultar" << endl;
        cout << " 4 - Excluir" << endl;
        cout << "Sua escolha: ";
        cin >> menu;
        fflush(stdin);
        switch(menu)
        {
        case 0:
            system("cls");
            cout << "Programa finalizado";
            getchar();
            break;
        case 1:
            system("cls");
            cout << "Informe o valor a ser inserido: ";
            cin >> num;
            fflush(stdin);
            if(num % 2 == 0)
                inserir(&par, num);
            else
                inserir(&impar, num);
            cout << "VALOR INSERIDO COM SUCESSO!";
            getchar();
            break;
        case 2:
            system("cls");
            if(testa_vazia(par))
            {
                cout << "ARVORE PAR VAZIA!";
            }
            else
            {
                cout << "ELEMENTOS PARES EM PRE-ORDEM: "<< endl;
                mostra_preordem(par);
                cout << "\n\nELEMENTOS PARES EM ORDEM SIMETRICA: "<< endl;
                mostra_inordem(par);
                cout << "\n\nELEMENTOS PARES EM POS-ORDEM: "<< endl;
                mostra_posordem(par);
            }
            cout << endl << endl;
            if(testa_vazia(impar))
            {
                cout << "ARVORE PAR VAZIA!";
            }
            else
            {
                cout << "ELEMENTOS IMPARES EM PRE-ORDEM: "<< endl;
                mostra_preordem(impar);
                cout << "\n\nELEMENTOS IMPARES EM ORDEM SIMETRICA: "<< endl;
                mostra_inordem(impar);
                cout << "\n\nELEMENTOS IMPARES EM POS-ORDEM: "<< endl;
                mostra_posordem(impar);
            }

            getchar();
            break;
        case 3:
            system("cls");
            if(testa_vazia(par) && testa_vazia(impar))
            {
                cout << "ARVORES VAZIAS!";
            }
            else
            {
                cout << "Informe o valor a ser consultado: ";
                cin >> num;
                fflush(stdin);
                if(num % 2 == 0)
                    if(consulta(par, num))
                        cout << "O valor pertence a arvore.";
                    else
                        cout << "O valor nao pertence a arvore.";
                else
                    if(consulta(impar, num))
                        cout << "O valor pertence a arvore.";
                    else
                        cout << "O valor nao pertence a arvore.";
            }
            getchar();
            break;
        case 4:
            system("cls");
            if(testa_vazia(par) && testa_vazia(impar))
            {
                cout << "ARVORES VAZIAS!";
            }
            else
            {
                cout << "Informe o valor a ser removido: ";
                cin >> num;
                fflush(stdin);
                if(num % 2 == 0)
                    remover(&par, num);
                else
                    remover(&impar, num);
                cout << "VALOR REMOVIDO COM SUCESSO.";
            }
            getchar();
            break;
        };

    }
    while(menu != 0);

}

