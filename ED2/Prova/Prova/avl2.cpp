#include <iostream>
#include <cstdlib>
using namespace std;

#include<iostream>

using namespace std;

typedef struct no
{
    int info;
    no* sae;
    no* sad;
    int altura;
} arvore_avl;

arvore_avl* esvaziar(arvore_avl* t)
{
    if(t == NULL)
        return NULL;
    esvaziar(t->sae);
    esvaziar(t->sad);
    delete t;
}

/// INICIO UTEIS
int altura(arvore_avl* t)
{
    return (t == NULL ? -1 : t->altura);
}

arvore_avl* singleRightRotate(arvore_avl* &t)
{
    arvore_avl* u = t->sae;
    t->sae = u->sad;
    u->sad = t;
    t->altura = max(altura(t->sae), altura(t->sad))+1;
    u->altura = max(altura(u->sae), t->altura)+1;
    return u;
}

arvore_avl* singleLeftRotate(arvore_avl* &t)
{
    arvore_avl* u = t->sad;
    t->sad = u->sae;
    u->sae = t;
    t->altura = max(altura(t->sae), altura(t->sad))+1;
    u->altura = max(altura(t->sad), t->altura)+1 ;
    return u;
}

arvore_avl* doubleLeftRotate(arvore_avl* &t)
{
    t->sad = singleRightRotate(t->sad);
    return singleLeftRotate(t);
}

arvore_avl* doubleRightRotate(arvore_avl* &t)
{
    t->sae = singleLeftRotate(t->sae);
    return singleRightRotate(t);
}


/// FIM UTEIS

arvore_avl* insere(int x, arvore_avl* t)
{
    if(t == NULL)
    {
        t = new arvore_avl();
        t->info = x;
        t->altura = 0;
        t->sae = t->sad = NULL;
    }
    else if(x < t->info)
    {
        t->sae = insere(x, t->sae);
        if(altura(t->sae) - altura(t->sad) == 2)
        {
            if(x < t->sae->info)
                t = singleRightRotate(t);
            else
                t = doubleRightRotate(t);
        }
    }
    else if(x > t->info)
    {
        t->sad = insere(x, t->sad);
        if(altura(t->sad) - altura(t->sae) == 2)
        {
            if(x > t->sad->info)
                t = singleLeftRotate(t);
            else
                t = doubleLeftRotate(t);
        }
    }

    t->altura = max(altura(t->sae), altura(t->sad))+1;
    return t;
}

arvore_avl* findMin(arvore_avl* t)
{
    if(t == NULL)
        return NULL;
    else if(t->sae == NULL)
        return t;
    else
        return findMin(t->sae);
}

arvore_avl* findMax(arvore_avl* t)
{
    if(t == NULL)
        return NULL;
    else if(t->sad == NULL)
        return t;
    else
        return findMax(t->sad);
}

arvore_avl* remove(int x, arvore_avl* t)
{
    arvore_avl* temp;

    // Element not found
    if(t == NULL)
        return NULL;

    // Searching for element
    else if(x < t->info)
        t->sae = remove(x, t->sae);
    else if(x > t->info)
        t->sad = remove(x, t->sad);

    // Element found
    // With 2 children
    else if(t->sae && t->sad)
    {
        temp = findMin(t->sad);
        t->info = temp->info;
        t->sad = remove(t->info, t->sad);
    }
    // With one or zero child
    else
    {
        temp = t;
        if(t->sae == NULL)
            t = t->sad;
        else if(t->sad == NULL)
            t = t->sae;
        delete temp;
    }
    if(t == NULL)
        return t;

    t->altura = max(altura(t->sae), altura(t->sad))+1;

    // If node is unbalanced
    // If left node is deleted, right case
    if(altura(t->sae) - altura(t->sad) == 2)
    {
        // right right case
        if(altura(t->sae->sae) - altura(t->sae->sad) == 1)
            return singleLeftRotate(t);
        // right left case
        else
            return doubleLeftRotate(t);
    }
    // If right node is deleted, left case
    else if(altura(t->sad) - altura(t->sae) == 2)
    {
        // left left case
        if(altura(t->sad->sad) - altura(t->sad->sae) == 1)
            return singleRightRotate(t);
        // left right case
        else
            return doubleRightRotate(t);
    }
    return t;
}



int getBalance(arvore_avl* t)
{
    if(t == NULL)
        return 0;
    else
        return altura(t->sae) - altura(t->sad);
}

void inorder(arvore_avl* t)
{
    cout<< "<";
    if(t != NULL)
    {
        inorder(t->sae);
        cout << t->info << " ";
        inorder(t->sad);
    }
    cout<< ">";
}

arvore_avl* init()
{
    return NULL;
}

void inserir(arvore_avl **a, int x)
{
    *a = insere(x, *a);
}

void remover(arvore_avl **a, int x)
{
    *a = remove(x, *a);
}

void mostrar(arvore_avl *a)
{
    inorder(a);
    cout << endl;
}

int main()
{
    arvore_avl *t;
    t = init();
    int valor;
    int op;
    do
    {
        system("cls");
        cout<< "0 - Sair\n1 - Inserir valor na arvore\n2 - Remover valor da arvore\n3 - Mostrar arvore\n4 - Limpar arvore";
        cout<< "\n->";
        cin>> op;
        cin.get();
        switch(op)
        {
        case 0:
            break;
        case 1:
            cout<< "Valor a ser inserido: ";
            cin>> valor;
            inserir(&t, valor);
            break;
        case 2:
            cout<< "Valor a ser removido: ";
            cin>> valor;
            remover(&t, valor);
            break;
        case 3:
            mostrar(t);
            cin.get();
            break;
        case 4:

            break;
        default:
            cout<< "Opcao invalida";
            cin.get();
        }
    }
    while(op != 0);

}
