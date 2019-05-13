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

bool consulta(arvore *t, int v)
{
    if(testa_vazia(t))
        return false;
    return t->info == v  || consulta(t->sae, v) || consulta(t->sad, v);
}

arvore* remover(arvore *t, int v)
{
    if(v < t->info)
        t->sae = remover(t->sae, v);
    else if(v < t->info)
        t->sad = remover(t->sad, v);
    else
    {
        //achou o elemento a ser removido
        if(t->sae == NULL && t->sad == NULL)
        {
            //É um nó folha
            delete(t);
            t = NULL;
        }
        else if(t->sae == NULL)
        {
            //Só tem filho a direita
            arvore *aux = t;
            t = t->sad;
            delete(aux);
        }
        else if(t->sad == NULL)
        {
            //Só tem filho a esquerda
            arvore *aux = t;
            t = t->sae;
            delete(aux);
        }
        else
        {
            arvore *aux  = t->sae;
            while(aux->sad != NULL)
                aux = aux->sad;
            t->info = aux->info;
            aux->info = v;
            t->sae = remover(t->sae, v);
        }
    }
    return t;
}
