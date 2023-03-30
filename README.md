# MedLabApp_backend
_Application de gestion pour laboratoire d'analyse médicale, partie backend_

<ol>
  <li>Brève description 
  <p>Nous souhaitons mettre en place un système informatisé pour la gestion quotidienne des travaux de laboratoire d'analyse médicale.</p>
  
  Ces travaux consistent à :

   <ul>
    <li>enregistrer un patient (nom du patient, code du patient, numéro de téléphone, examens à effectuer)</li>
    <li>effectuer les analyses, produire les résultats des examens et les fournir au patient</li>
    <li>conserver les données (les archives) sur les opérations effectuées</li>
   </ul>
  </li>
  <li> Fonctionnalités 
   <ul>
    <li>enregistrer des examens (type d’examens à faire) : code de l’examen (unique), intitulé, commentaires, prix.</li>
    <li>enregistrer un patient. Pendant l’enregistrement du patient, l’on doit pouvoir choisir les examens que ce dernier vient faire. Un patient peut faire plusieurs examens. Et chaque examen peut être effectué par plusieurs patients. Il vient avec l’échantillon à examiner (identifié par un code) ou bien cet échantillon est prélevé sur lui </li>
    <li>enregistrer le résultat de l’analyse d’un examen du patient. Donc lors de l’enregistrement d’un résultat, l’on doit préciser le patient et l’examen concerné (par exemple : rechercher et choisir un patient, ensuite, choisir un des examens du patient n’ayant encore de résultat et enfin renseigner le champ de résultat (positif ou non)). [il conviendrait de préciser tous les champs possibles que l’on peut avoir pour un résultat… ]</li>
    <li>afficher l’historique des examens effectués pour une date (ou interval de date) donnée </li>
    <li>ressortir un graphe indiquant le nombre d'analyses effectué par jour sur une période (à préciser. ?)</li>
    <li>créer un compte et se connecter  (si nécessaire; au cas où chaque utilisateur doit avoir son espace réservé…). Seuls les administrateurs peuvent créer des comptes dans ce cas. Chacun peut changer son mot de passe.</li>
   </ul>
  </li>
  
  <li> Utilisateurs et cas d'utiliations 
    <ul>
     <li> administrateur
        <ol>
          <li> CRUD Agent </li>
          <li> Modification compte Admin
          <li> Consultation des différents stats</li>
          <li> CRUD examen
         </ol>
     </li>
     <li> Agent 
      <ol>
       <li>Enregistrer un patient</li>
       <li>Faire passer un examen à un patient</li>
       <li>Enregistrer les résultats</li>
      </ol>
     </li>
     </ul>
  </li>
  <li> Divers 
    <p>  Nous nous proposons de réaliser ce système que nous nommons MedLab App.</p>
  </li>
   
 </ol>
