typedef struct arv {
    int info;
    arv *sae;
    arv *sad;
} ARVORE;

//funções
ARVORE* cria_arvore();
bool treeIsEmpty(ARVORE *t);
void mostraArvorePreOrdem(ARVORE *t);
void mostraArvoreSimetrica(ARVORE *t);
void mostraArvorePosOrdem(ARVORE *t);
void inserir(ARVORE **t, int num);
ARVORE* remover(ARVORE **t, int num);
int acharAltura(ARVORE *t);
ARVORE *limparArvore(arv *t);
bool busca(ARVORE *t, int valor);
int buscaNivel(ARVORE *t, int valor, int nivel);
int contarFolhas(ARVORE *t, int soma);
int comparar(ARVORE *t, int x, int soma);

ARVORE* cria_arvore() {
    return NULL;
}

bool treeIsEmpty(ARVORE *t) {
    if(t == NULL){
        return true;
    }
    if(t->info == NULL){
        return true;
    }
    return false;
}

void mostraArvorePreOrdem(ARVORE *t) {
    cout << "<";
    if(!treeIsEmpty(t)){
        cout << t->info;
        mostraArvorePreOrdem(t->sae);
        mostraArvorePreOrdem(t->sad);
    }
    cout << ">";
}

void mostraArvoreSimetrica(ARVORE *t) {
    cout << "<";
    if(!treeIsEmpty(t)){
        mostraArvoreSimetrica(t->sae);
        cout << t->info;
        mostraArvoreSimetrica(t->sad);
    }
    cout << ">";
}

void mostraArvorePosOrdem(ARVORE *t) {
    cout << "<";
    if(!treeIsEmpty(t)){
        mostraArvorePosOrdem(t->sae);
        mostraArvorePosOrdem(t->sad);
        cout << t->info;
    }
    cout << ">";
}

void inserir(ARVORE **t, int num) {
    if(*t == NULL) {
        *t = new ARVORE;
        (*t)->sae = NULL;
        (*t)->sad = NULL;
        (*t)->info = num;
    }else{
        if(num < (*t)->info) {
            inserir(&(*t)->sae, num);
        }else{
            inserir(&(*t)->sad, num);
        }
    }
}

ARVORE* remover(ARVORE **t, int num){
    if(num == (*t)->info){
        ARVORE *aux = *t;
        //folha
        if((*t)->sae == NULL && (*t)->sad == NULL){
            delete(aux);
            (*t) = NULL;
        }else{
            //só filho a direita
            if((*t)->sae == NULL){
                (*t) = (*t)->sad;
                aux->sad = NULL;
                delete(aux);
                aux = NULL;
            }else{
                if((*t)->sad == NULL){
                    (*t) = (*t)->sae;
                    aux->sae = NULL;
                    delete(aux);
                    aux = NULL;
                }else{
                    aux = (*t)->sae;
                    while(aux->sad != NULL){
                        aux = aux->sad;
                    }
                    (*t)->info = aux->info;
                    aux->info = num;
                    (*t)->sae = remover(&(*t)->sae, num);
                }
            }
        }
    }
    else if(num < (*t)->info){
        remover(&(*t)->sae, num);
    }else{
        remover(&(*t)->sad, num);
    }
    return *t;
}

int acharAltura(ARVORE *t){
    if(t == NULL){
        return -1;
    }
    int esquerda = acharAltura(t->sae);
    int direita = acharAltura(t->sad);
    return max(esquerda, direita) + 1;
}

ARVORE *limparArvore(arv *t) {
    if(t != NULL){
        limparArvore(t->sae);
        limparArvore(t->sad);
        delete (t);
    }
    return NULL;
}

bool busca(ARVORE *t, int valor){
    if(treeIsEmpty(t)){
        return false;
    }
    return t->info == valor || busca(t->sae, valor) || busca(t->sad, valor);
}

int buscaNivel(ARVORE *t, int valor, int nivel) {
    if(t->info == valor){
        return nivel;
    }
    if(valor < t->info){
        nivel++;
        return buscaNivel(t->sae, valor, nivel);
    } else {
        nivel++;
        return buscaNivel(t->sad, valor, nivel);
    }
}

int contarFolhas(ARVORE *t, int soma){
    if(!treeIsEmpty(t->sae)){
        soma = contarFolhas(t->sae, soma);
    }
    if(!treeIsEmpty(t->sad)){
        soma = contarFolhas(t->sad, soma);
    }
    if(treeIsEmpty(t->sae) && treeIsEmpty(t->sad)){
        soma = soma + 1;
    }
    return soma;
}

int comparar(ARVORE *t, int x, int soma){
    if(treeIsEmpty(t)){
        return soma;
    }

    if(t->info > x) {
        soma++;
        soma = comparar(t->sae, x, soma);
        soma = comparar(t->sad, x, soma);
    } else {
        soma = comparar(t->sad, x, soma);
    }
    return soma;
}

