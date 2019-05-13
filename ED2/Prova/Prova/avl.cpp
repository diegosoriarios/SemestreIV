#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

typedef struct arv
{
    int info;
    int alt;
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

int mostra_folha(arvore *t, int &cont)
{
    if(!testa_vazia(t))
    {
        if((t->sae == NULL) && (t->sad == NULL))
        {
            cont++;
            return cont;
        }
        mostra_folha(t->sae, cont);
        mostra_folha(t->sad, cont);
    }
    return cont;
}

int mostra_maior(arvore *t, int valor, int &cont)
{
    if(!testa_vazia(t))
    {
        if(t->info > valor)
        {
            cont++;
            //return cont;
        }
        mostra_maior(t->sae, valor, cont);
        mostra_maior(t->sad, valor, cont);
    }
    return cont;
}

int mostra_filho_unico(arvore *t, int &cont)
{
    if(!testa_vazia(t))
    {
        //if((t->sae == NULL && t->sad != NULL) || (t->sae != NULL && t->sad == NULL))
        if(t->sae == NULL ^ t->sad == NULL)
        {
            cont++;
        }
        mostra_filho_unico(t->sae, cont);
        mostra_filho_unico(t->sad, cont);
    }
    return cont;
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

bool consulta(arvore *t, int v)
{
    if(testa_vazia(t))
        return 0;
    return t->info == v || consulta(t->sae, v) || consulta(t->sad,v);
}

int alt(arvore *t)
{
    if (!t)
        return -1;
    else
        return t->alt;
}

int fatorBalanceamento (arvore *t){
    return labs(alt(t->sae) - alt(t->sad));
}

int maior(int x, int y){
    if(x > y)
        return x;
    return y;
}

void rotLL(arvore *t)
{
    arvore *a;
    a = t->sae;
    t->sae = a->sad;
    a->sad = t;
    t->alt = maior(alt(t->sae), alt(t->sad)) + 1;
    a->alt = maior(alt(a->sae), t->alt) + 1;
    t = a;
}

void rotRR(arvore *t)
{
    arvore *a;
    a = t->sad;
    t->sad = a->sae;
    a->sae = t;
    t->alt = maior(alt(t->sae), alt(t->sad)) + 1;
    a->alt = maior(alt(a->sad), t->alt) + 1;
    t = a;
}

void rotLR(arvore *t)
{
    rotRR(t->sae);
    rotLL(t);
}

void rotRL(arvore *t)
{
    rotLL(t->sad);
    rotRR(t);
}

/*int inserir(arvore *t, int v)
{
    int res;
    //arvore esta vazia
    if(!t)
    {
        t = new arvore;
        t->sae = NULL;
        t->sad = NULL;
        t->info = v;
        t->alt = 0;
    }
    else
    {
        arvore atual = *t;
        if(v < atual.info)
        {
            if((res = inserir((atual.sae), v)) == 1)
            {
                if(fatorBalanceamento(&atual) >= 2)
                {
                    if(v < (*t).sae->info)
                        rotLL(t);
                    else
                        rotLR(t);
                }
            }
        }
        else
        {
            if(v > atual.info)
            {
                if((res = inserir((atual.sad), v)) == 1)
                {
                    if(fatorBalanceamento(&atual) >= 2)
                    {
                        if(v < (*t).sad->info)
                            rotRR(t);
                        else
                            rotRL(t);
                    }
                }
            }
        }
        atual.alt = maior(alt(atual.sae), alt(atual.sad)) + 1;
        return res;
    }
}*/

int inserir(arvore **t, int v)
{
    int res;
    //arvore esta vazia
    if(*t == NULL)
    {
        arvore *a = new arvore;
        a->sae = NULL;
        a->sad = NULL;
        a->info = v;
        a->alt = 0;
        cout << a->info << endl;
        *t = a;
        return 1;
    }
    else
    {
        arvore atual = **t;
        if(v < atual.info)
        {
            if((res = inserir(&(atual.sae), v)) == 1)
            {
                if(fatorBalanceamento(&atual) >= 2)
                {
                    if(v < (**t).sae->info)
                        rotLL(*t);
                    else
                        rotLR(*t);
                }
            }
        }
        else
        {
            if(v > atual.info)
            {
                if((res = inserir(&(atual.sad), v)) == 1)
                {
                    if(fatorBalanceamento(&atual) >= 2)
                    {
                        if(v < (**t).sad->info)
                            rotRR(*t);
                        else
                            rotRL(*t);
                    }
                }
            }
        }
        atual.alt = maior(alt(atual.sae), alt(atual.sad)) + 1;
        return res;
    }
}

arvore* procuraMenor(arvore* atual)
{
    arvore *no1 = atual;
    arvore *no2 = atual->sae;
    while(no2 != NULL)
    {
        no1 = no2;
        no2 = no2->sae;
    }
    return no1;
}

int remover(arvore **t, int v)
{
    if(*t == NULL)
    {
        cout << "Valor nao existe" << endl;
        return 0;
    }
    int res;
    if(v < (*t)->info)
    {
        if((res = remover(&(*t)->sae, v)) == 1)
            {
                if(fatorBalanceamento(*t) >= 2)
                {
                    if(alt((*t)->sad->sae) <= alt((*t)->sad->sad))
                        rotRR(*t);
                    else
                        rotRL(*t);
                }
            }
    }
    if(v > (*t)->info)
    {
        if((res = remover(&(*t)->sad, v)) == 1)
        {
            if(fatorBalanceamento(*t) >= 2)
            {
                if(alt((*t)->sae->sad) <= alt((*t)->sae->sae))
                    rotLL(*t);
                else
                    rotLR(*t);
            }
        }
    }
    if(v == (*t)->info)
    {
        if(((*t)->sae == NULL) || ((*t)->sad == NULL))
        {
            arvore *old = *t;
            if((*t)->sae != NULL)
                *t = (*t)->sae;
            else
                *t = (*t)->sad;
            delete(old);
            (*t) = NULL;
        }
        else
        {
            arvore *temp = procuraMenor((*t)->sad);
            (*t)->info = temp->info;
            remover(&(*t)->sad, (*t)->info);
            if(fatorBalanceamento(*t) >= 2)
            {
                if(alt((*t)->sae->sad) <= alt((*t)->sae->sae))
                    rotLL(*t);
                else
                    rotLR(*t);
            }
        }
        return 1;
    }
    return res;
}

main()
{
    int menu = -1, num, cont = 0;
    arvore *a = cria_arvore();
    do
    {
        system("cls");
        cout << "MENU DE OPCOES" << endl;
        cout << " 0 - Sair" << endl;
        cout << " 1 - Incluir" << endl;
        cout << " 2 - Mostrar" << endl;
        cout << " 3 - Consultar" << endl;
        cout << " 4 - Excluir" << endl;
        cout << " 10 - Deleta arvore" << endl;
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
            inserir(&a, num);
            cout << "VALOR INSERIDO COM SUCESSO!";
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
                cout << "ELEMENTOS EM PRE-ORDEM: "<< endl;
                mostra_preordem(a);
                cout << "\n\nELEMENTOS EM ORDEM SIMETRICA: "<< endl;
                mostra_inordem(a);
                cout << "\n\nELEMENTOS EM POS-ORDEM: "<< endl;
                mostra_posordem(a);
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
                cout << "Informe o valor a ser consultado: ";
                cin >> num;
                fflush(stdin);
                if(consulta(a, num))
                    cout << "O valor pertence a arvore.";
                else
                    cout << "O valor nao pertence a arvore.";
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
                cout << "Informe o valor a ser removido: ";
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

        case 10:
            system("cls");
            if(testa_vazia(a))
                cout << "ARVORE VAZIA!";
            else
            {
                while(a->sae != NULL || a->sad != NULL)
                    remover(&a, a->info);
                delete(a);
                a = NULL;
                cout << "EXCLUSAO REALIZADA COM SUCESSO" << endl;
            }
            getchar();
            break;

        };

    }
    while(menu != 0);

}

