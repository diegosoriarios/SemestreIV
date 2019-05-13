#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

typedef struct livro
{
    int isbn;
    string autor;
    string titulo;
    string editora;
    int ano;
    float preco;
} item;

typedef struct arv
{
    item info;
    arv *sad;
    arv *sae;
} arvore;

arvore* cria_arvore()
{
    return NULL;
}

int testa_vazia(arvore *t)
{
    return t == NULL;
}

void inserir(arvore **t, item *v)
{
    //arvore esta vazia
    if(*t == NULL)
    {
        *t = new arvore;
        (*t)->sae = NULL;
        (*t)->sad = NULL;
        (*t)->info = *v;
    }
    else
    {
        if(v->isbn < (*t)->info.isbn)
            inserir(&(*t)->sae, v);
        else
            inserir(&(*t)->sad, v);
    }
}

void mostra(arvore *t)
{
    if(!testa_vazia(t))
    {
        mostra(t->sae);
        cout << t->info.isbn << "\t" << t->info.titulo << "\t" << t->info.autor << "\t" << t->info.ano << endl;
        mostra(t->sad);
    }
}

bool consulta(arvore *t, int v)
{
    if(testa_vazia(t))
        return 0;

    return t->info.isbn == v || consulta(t->sae, v) || consulta(t->sad,v);
}

bool consulta_isbn(arvore *t, int v)
{
    if(testa_vazia(t))
        return 0;

    if(t->info.isbn == v)
    {
        cout << "CODIGO ISBN: " << t->info.isbn << endl;
        cout << "AUTOR: " << t->info.autor << endl;
        cout << "TITULO: " << t->info.titulo << endl;
        cout << "EDITORA: " << t->info.editora << endl;
        cout << "ANO: " << t->info.ano << endl;
        cout << "PRECO: " << t->info.preco << endl;
    }

    return t->info.isbn == v || consulta_isbn(t->sae, v) || consulta_isbn(t->sad,v);
}

bool consulta_titulo(arvore *t, string v)
{
    if(testa_vazia(t))
        return 0;

    if(t->info.titulo == v)
    {
        cout << "CODIGO ISBN: " << t->info.isbn << endl;
        cout << "AUTOR: " << t->info.autor << endl;
        cout << "TITULO: " << t->info.titulo << endl;
        cout << "EDITORA: " << t->info.editora << endl;
        cout << "ANO: " << t->info.ano << endl;
        cout << "PRECO: " << t->info.preco << endl;
    }

    return t->info.titulo == v || consulta_titulo(t->sae, v) || consulta_titulo(t->sad,v);
}

arvore* remover(arvore **t, int v)
{
    if(v < (*t)->info.isbn)
        remover(&(*t)->sae, v);
    else if(v > (*t)->info.isbn)
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
            aux->info.isbn = v;
            (*t)->sae = remover(&(*t)->sae, v);
        }
    }
    return *t;
}
