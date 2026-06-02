import { createRouter, createWebHistory } from 'vue-router';

import wwPage from './views/wwPage.vue';

import { initializeData, initializePlugins, onPageUnload } from '@/_common/helpers/data';

let router;
const routes = [];

function scrollBehavior(to) {
    if (to.hash) {
        return {
            el: to.hash,
            behavior: 'smooth',
        };
    } else {
        return { top: 0 };
    }
}

 
/* wwFront:start */
import pluginsSettings from '../../plugins-settings.json';

// eslint-disable-next-line no-undef
window.wwg_designInfo = {"id":"7b8a5b79-fa88-4f69-94a2-4e22ce155fd7","homePageId":"d4f7588a-fc42-4971-8154-7c897875ee5d","authPluginId":null,"baseTag":null,"defaultTheme":"light","langs":[{"lang":"en","default":true}],"background":{},"workflows":[],"pages":[{"id":"7ebcfb1d-5192-435e-8e70-9e2c5ac0d057","linkId":"7ebcfb1d-5192-435e-8e70-9e2c5ac0d057","name":"C - Account Page","folder":null,"paths":{"en":"accounts","default":"accounts"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"3b963e0a-5b5e-41e7-966c-bb0560763c41","sectionTitle":"Top Nav","linkId":"9b5e552a-35b0-4113-a872-fa9b143a9a3d"},{"uid":"954ba2e2-8295-4291-a955-8d98e2a8d076","sectionTitle":"Hero","linkId":"0d7cb683-023a-4852-87af-abf399c82561"},{"uid":"1d873665-fdc1-4374-832c-021e183c5065","sectionTitle":"Top Nav Sheet","linkId":"eab7d913-9ebf-4ff7-9c42-6eece4395b09"},{"uid":"8e80c243-0de2-45bf-bc09-dfb791c1aeb1","sectionTitle":"Account Listing","linkId":"6a512666-1891-4bea-853f-78aed44993dc"},{"uid":"f972d3d5-85c6-44b8-a7ee-fff61ee130fd","sectionTitle":"Articles","linkId":"a825ea19-61fb-48cd-81d6-31e95ff8edd7"},{"uid":"0c9f222a-1e01-410f-a797-526ded6cd2b8","sectionTitle":"Footer","linkId":"fcf73ecd-f6a5-477b-8d4c-c79c8ac00f07"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"b6f035a2-96ef-41f8-a385-94a1ea48f63d","linkId":"b6f035a2-96ef-41f8-a385-94a1ea48f63d","name":"1-Banking","folder":null,"paths":{"en":"banking","default":"banking"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"b5921962-05d5-4414-b2a7-3b502fe0f5a4","sectionTitle":"Top Nav","linkId":"04e491a0-cc07-4071-8454-843cb8f32244"},{"uid":"db3a6682-4654-4e6f-8ff0-6d9e70a22bb4","sectionTitle":"Hero","linkId":"fbbd4b9d-0d2b-468a-a9ac-3ca0b9020498"},{"uid":"244610a1-ffb5-460e-858c-1b32f151eb7a","sectionTitle":"Buttons","linkId":"c4fbbcd6-b864-441c-8191-b194ef91413b"},{"uid":"93d2b029-ae6d-4fb2-8774-7e3e3313158e","sectionTitle":"Accounts","linkId":"2bd609ff-d473-4430-9795-30fde539eb30"},{"uid":"a1f45712-6b7a-4f01-8689-2b3bac0c4715","sectionTitle":"Top Articles","linkId":"731ec0b8-82c5-4b1f-ac49-35858c55c7b4"},{"uid":"bc48f34c-717e-4169-b72f-7efc46809e7f","sectionTitle":"ModalA","linkId":"6db94fea-5f7d-49da-bc0f-b212e2eb7e45"},{"uid":"9f352b00-7b0f-4774-be64-85a6dd2e1849","sectionTitle":"Cards","linkId":"fea0b6e6-8432-4044-83cc-2c8283c52605"},{"uid":"7235947b-bef8-4bfc-a68f-b4be00c6c5cb","sectionTitle":"ModalCards","linkId":"e70db2d6-e677-4255-a48d-d797cf337249"},{"uid":"f65eb95d-0d67-4ceb-921e-da4aafd30068","sectionTitle":"Financing","linkId":"bf9b3102-29d6-40f2-b154-f50c46a42f5c"},{"uid":"a701e1e0-a363-4120-979f-93a909258dae","sectionTitle":"Articles","linkId":"acf20ea2-a8bf-4e98-9dfe-604678127a16"},{"uid":"3b2bead9-9746-4d6a-a7ab-f4ac2db621d9","sectionTitle":"Footer","linkId":"48f907e1-73a3-4ee4-825c-96e5ec4b5475"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"0b18aacf-73dd-465c-91fe-cd30d415f951","linkId":"0b18aacf-73dd-465c-91fe-cd30d415f951","name":"P - Funds Page","folder":null,"paths":{"en":"investment/funds","default":"investment/funds"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"cf1e66c2-c6b1-436b-9a34-be8035b001ed","sectionTitle":"Top Nav","linkId":"eca601b4-185d-480d-9e70-166d53bc9759"},{"uid":"74c40846-9014-43f9-a914-0cc6b33d0add","sectionTitle":"Hero","linkId":"68d891da-aab2-486f-889a-ee934480527d"},{"uid":"0e94be0e-e8f5-481a-8dee-00207c146811","sectionTitle":"Product Listing - Filter","linkId":"b97bc41a-d8b0-4cf1-9324-892399124898"},{"uid":"fe0d329f-ed02-45d5-a5e2-9890c7911621","sectionTitle":"Footer","linkId":"b82bf536-dea4-4278-8452-62586c91e4d4"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"d744301f-64bc-4e64-8662-835794d3d585","linkId":"d744301f-64bc-4e64-8662-835794d3d585","name":"P - Calculator","folder":null,"paths":{"en":"calculator","default":"calculator"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"fa6fa44b-92f6-4c1e-b9c7-d63d32cfead0","sectionTitle":"Top Nav","linkId":"6d7e7eb2-2c3f-426a-a407-e0e7c91c7aa0"},{"uid":"05d63150-4504-4a37-a048-24458c67329b","sectionTitle":"Hero","linkId":"acb320a5-d013-423b-ae61-9d829ce9658c"},{"uid":"0a2ec3e6-169e-411c-a5c7-3c9890fb662a","sectionTitle":"Calculator","linkId":"03aca389-d73a-48c7-b6cc-dcd0219c18a2"},{"uid":"b2aad434-1b0f-42d8-aade-00fe63061d1b","sectionTitle":"Graph","linkId":"fb0cf863-68b8-4624-8289-0f970ce7fc41"},{"uid":"1e431591-bffa-436a-a0f1-55135104c84f","sectionTitle":"Articles","linkId":"b62c4588-4a72-46e5-9d0a-08fc087d5214"},{"uid":"36118446-f981-4e2e-bed6-63e3b1cb3215","sectionTitle":"Footer","linkId":"648e9b22-625f-42b1-af49-4adceab00825"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"b9295ac1-8b5c-47f4-b07e-cdf7bd4f9716","linkId":"b9295ac1-8b5c-47f4-b07e-cdf7bd4f9716","name":"S - Fund","folder":null,"paths":{"en":"{{param|32e44605-598b-4c13-bf50-a7e928e7d8f6}}","default":"{{param|32e44605-598b-4c13-bf50-a7e928e7d8f6}}"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"2457616d-3052-4f1c-ab7c-b686e11e2660","sectionTitle":"Top Nav","linkId":"0702c258-9a3d-455a-bcee-bf043cde2e15"},{"uid":"83075e0a-f455-4de4-a4ce-c1b8a6a1fc02","sectionTitle":"Fund Name","linkId":"ae3610a4-0ae4-4c44-8f14-ecdcdaf19b8f"},{"uid":"0ba1ce98-0aad-4bb7-84b5-64eed9dae064","sectionTitle":"Fund Details","linkId":"11ea348a-c536-4129-a738-9fd22d3d1a54"},{"uid":"c5052717-0736-4882-ac75-a2fa7747f8ad","sectionTitle":"Footer","linkId":"9120e632-5a6b-460b-ae14-6791ce92cb09"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"65030727-f96f-4f40-9d49-599f666de514","linkId":"65030727-f96f-4f40-9d49-599f666de514","name":"3-Insight","folder":null,"paths":{"en":"insight2","default":"insight2"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"c90ed2c8-a864-4862-916e-a9243537849a","sectionTitle":"Top Nav","linkId":"9e0ade0b-a278-4be3-9dbe-342da98eebad"},{"uid":"0330f5e3-8f3b-4fb7-8dd4-fa9e69a404bc","sectionTitle":"Hero","linkId":"89676a7c-b28a-48ff-8b00-628f65489297"},{"uid":"1d15e03f-e147-4662-ba04-edaa1290fb88","sectionTitle":"Buttons","linkId":"8c23e5bb-03c6-4900-a852-4b0e558b26e2"},{"uid":"113e4003-278f-4bef-9146-83cc7cba9e28","sectionTitle":"Top Pick","linkId":"bbb4a8f1-f46c-4793-9000-3a7aae716bd9"},{"uid":"b655a0d9-3a19-4d90-896b-6cfeddf3b305","sectionTitle":"Top Articles","linkId":"32a61b89-30a6-4c76-b096-4529f1e5d01c"},{"uid":"15f1abad-b150-4a47-967b-fae1e3d8f491","sectionTitle":"Accounts","linkId":"feee44df-d37e-45fc-83e2-36e81fdcd961"},{"uid":"967bf9d7-2514-4103-8658-278a4a907b85","sectionTitle":"Footer","linkId":"dad91fa6-4956-43bc-833e-57ba79626d87"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"da994735-5445-4bff-a269-96c94c9a562c","linkId":"da994735-5445-4bff-a269-96c94c9a562c","name":"C - Cards Page","folder":null,"paths":{"en":"banking/cards","default":"banking/cards"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"9b815aa4-705b-46d6-b790-c0da1eb547f7","sectionTitle":"Top Nav","linkId":"a304d00f-fc0a-4a40-b5cf-1a394463f832"},{"uid":"4ab00491-53b7-46ac-ab8d-7f3607d8f084","sectionTitle":"Hero","linkId":"4bb0bb5f-02e3-4eda-88c7-323177d01d96"},{"uid":"8bdc8abf-dd43-4e75-9c4d-50f98f663490","sectionTitle":"Product Listing - Filter","linkId":"f2d45dd7-1a20-4b0e-82ab-db3616c34e4d"},{"uid":"f859843f-f0ab-41ec-b5eb-bf1055ae18d9","sectionTitle":"Articles","linkId":"42ac922c-97c9-43e9-aa2f-8767fef57963"},{"uid":"2256c1b1-5ebc-4e8f-ab73-966e10ee7a84","sectionTitle":"Footer","linkId":"cb016285-02f1-461b-a361-6a1708edd832"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"c360e516-149c-47fe-8bbc-ca7718406459","linkId":"c360e516-149c-47fe-8bbc-ca7718406459","name":"S - Article","folder":null,"paths":{"en":"article/{{param|6c5b4a3d-2e1f-4b0a-9f8e-7d6c5b4a3d2e}}-copy","default":"article/{{param|6c5b4a3d-2e1f-4b0a-9f8e-7d6c5b4a3d2e}}-copy"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"240bb82d-0ec8-4faf-b32e-9a9980cd7437","sectionTitle":"Top Nav","linkId":"59fca44f-e155-453e-bdca-937837a90e36"},{"uid":"421d6cfc-112c-45d0-93c4-0d5325868215","sectionTitle":"Header","linkId":"9b5421c8-31ab-46f6-a2dd-347e65bafab9"},{"uid":"4f364b2e-0b20-4142-b17f-bec8f77faa42","sectionTitle":"Image","linkId":"1457e986-93a5-4109-90a6-2ec5ac5231dd"},{"uid":"2a09954a-a061-4bc3-898f-8bf4382a39a6","sectionTitle":"Content","linkId":"2d45eb58-8fb1-4e56-9f42-cac09667a3e5"},{"uid":"bffd1dd9-9ab1-40bc-9a7e-7f9c9497f413","sectionTitle":"More","linkId":"4d8b3cba-b003-4ca8-a3a9-edf4094dce4d"},{"uid":"15bcb735-4b2a-4764-8c94-1d6a4870694e","sectionTitle":"Footer","linkId":"54ae54b1-acfa-4c6c-b21e-79e9746c1f4a"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"d4f7588a-fc42-4971-8154-7c897875ee5d","linkId":"d4f7588a-fc42-4971-8154-7c897875ee5d","name":"Home","folder":null,"paths":{"en":"home","default":"home"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"abb88297-7b5f-4a7e-bf40-0c0663501f10","sectionTitle":"Top Nav","linkId":"6ba1aae9-1a42-4736-a255-665b316d82b3"},{"uid":"3593a9fc-0b08-4fb2-a2ae-bf74cb8c577d","sectionTitle":"Hero 2","linkId":"269a44f0-74bc-4fd1-a451-ac3c2988241c"},{"uid":"a9933482-bdde-4959-a811-2c5701f3cdb1","sectionTitle":"Selection","linkId":"e93053a9-8208-4975-8b21-fdf55a1002a4"},{"uid":"8c1e7780-50c3-49ae-adac-0eb5c9fd9679","sectionTitle":"Section","linkId":"b41a241e-9ba2-451c-8f4c-6681eade6ce9"},{"uid":"54be0ef7-2ce1-426f-87a1-e77c4fec47c1","sectionTitle":"Hero","linkId":"8389b704-3d3f-47dd-a289-89e5b22d59c9"},{"uid":"0595fb3b-525f-4890-8b16-d3334217fbf4","sectionTitle":"Articles","linkId":"b7cdbaa8-b6f9-454d-a3bb-741ec1ae6b1e"},{"uid":"3939e0ae-1b5a-4659-9625-548a3879a764","sectionTitle":"Footer","linkId":"f1f9421f-1abb-4fe3-b559-33a11130385d"}],"pageUserGroups":[],"title":{"en":"RES Exam Bank","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"f69ab0c7-d1f4-407e-9c9d-7c37525cd0a2","linkId":"f69ab0c7-d1f4-407e-9c9d-7c37525cd0a2","name":"C - Articles Page","folder":null,"paths":{"en":"articles","default":"articles"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"111b8403-5862-4d19-8f92-1be728f3174d","sectionTitle":"Top Nav","linkId":"932bbab3-bd74-4e6a-88ba-9fec4545e9bf"},{"uid":"d8c75b42-4675-4b44-b79b-a0487fe9cdf4","sectionTitle":"Hero","linkId":"809232d4-ade1-43e6-b9a7-295e10e551a7"},{"uid":"4989bd82-5855-41bf-a1ff-cdce37bbab7a","sectionTitle":"Product Listing","linkId":"40ec2deb-a8d0-44ea-b703-443b6ecfb0ba"},{"uid":"faccd472-05ec-4189-b9cd-73287a677618","sectionTitle":"Articles","linkId":"84ee203a-d9ea-4bdd-8e4f-66ec37666a73"},{"uid":"5e2c7a7c-5da6-4f8b-9cbb-85da800b4abd","sectionTitle":"Footer","linkId":"72767c3d-5060-4579-bfa4-8672a41afaed"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"19ae602f-2cbe-49ba-be7b-3e615caea97f","linkId":"19ae602f-2cbe-49ba-be7b-3e615caea97f","name":"2-Investment","folder":null,"paths":{"en":"investment","default":"investment"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"da6340b2-5de7-4c2f-99f1-f5cad30b5bfe","sectionTitle":"Top Nav","linkId":"da9ca1e3-59fd-4136-b694-9ee489ac0676"},{"uid":"4ca6be78-0b6d-4008-8d6f-7cb04b4ac7ff","sectionTitle":"Hero","linkId":"ec25ce68-e482-42df-8154-a0867b7b63b5"},{"uid":"37c47aa8-5b50-4bca-96a9-902d610a5589","sectionTitle":"Buttons","linkId":"ca154794-6d52-4398-8e4c-7dc16fabb069"},{"uid":"b72421a8-01d3-4c3d-9354-ceecc2769eff","sectionTitle":"Top Pick","linkId":"9ce1b5d8-e73c-4a9f-88de-42f556e03b96"},{"uid":"e9ee193a-f598-496c-9927-68db7d6cafba","sectionTitle":"Funds","linkId":"6b2a8eae-1ed1-4e90-9795-093264391740"},{"uid":"78867a88-8e6b-45c8-9634-e58005996e83","sectionTitle":"Top Articles","linkId":"9d7e3038-519f-4954-a1b2-f955b1b43fe2"},{"uid":"1627ccbf-5584-4ced-a5c5-f9043bb259bb","sectionTitle":"Table Stocks","linkId":"b37e1664-d423-435e-b511-a65e3e015170"},{"uid":"50d8f198-2ba3-4d84-8247-dd39383a343c","sectionTitle":"Accounts","linkId":"00d313ba-92a5-4fb5-a928-b9c480a3ef63"},{"uid":"1cdcf203-7748-4712-b642-7908e257d690","sectionTitle":"Articles","linkId":"a233b2c2-d5c1-4dc7-8f59-e72cc49c1b4c"},{"uid":"c13cdb25-5c11-4d7d-9034-fe71c5edfe91","sectionTitle":"Section","linkId":"78f48c08-65ed-46b4-bbac-3c0851989b7a"},{"uid":"c541878b-7027-4cdb-ae91-69494223c7ff","sectionTitle":"Footer","linkId":"5d15fa99-8e4f-4c77-a722-c381516c6985"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"aa8e2148-ba46-40d6-8d17-2f7de06313c3","linkId":"aa8e2148-ba46-40d6-8d17-2f7de06313c3","name":"P - Stock Page","folder":null,"paths":{"en":"stocks","default":"stocks"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"b4173738-1897-4718-bf93-96de266aaeb6","sectionTitle":"Top Nav","linkId":"ba75d99c-5b8a-4aec-b7b5-6957503b9593"},{"uid":"df1f4f0a-758d-4019-92a5-fa418ca1cba4","sectionTitle":"Hero","linkId":"447caf25-4b22-4ac8-837d-c5ee516cc657"},{"uid":"a3d3d0eb-6907-451d-b185-add897e45042","sectionTitle":"Top Pick","linkId":"2c314fcf-9a34-459c-a3f5-e740e9277def"},{"uid":"7f08a54c-6202-4791-a0df-7dba520aa11b","sectionTitle":"Stock Table","linkId":"99f2e423-2ab7-496e-907b-daa77a8d3865"},{"uid":"029f2a0a-a0f4-44be-a1cd-03c551d65f00","sectionTitle":"Articles","linkId":"8aafd126-2d78-4dbe-8f85-c5945950357b"},{"uid":"37c516f9-b770-47b3-a8b9-4e7c2a131fd4","sectionTitle":"Footer","linkId":"ab285910-d02a-4c60-bc62-6e29b78e33c9"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"db8ea4e1-9fd0-48d3-86ea-e33c4017f636","linkId":"db8ea4e1-9fd0-48d3-86ea-e33c4017f636","name":"4-About Us","folder":null,"paths":{"en":"about-us","default":"about-us"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"3b5a8f2e-8f22-4617-bbbd-e60cc41be225","sectionTitle":"Top Nav","linkId":"a64b0363-d3b7-4553-a5c9-34e68bae8027"},{"uid":"6bb9b27a-3bd9-491a-81b5-6118cb845d38","sectionTitle":"Hero","linkId":"d003e77c-cece-4189-9064-40e17bcc14e4"},{"uid":"4ce7b7bc-8d2d-4de0-9fef-bcbc1fcceead","sectionTitle":"Banner","linkId":"c5a110f4-bcb9-4353-9d61-05a4009d71cd"},{"uid":"f87c94c0-c429-446b-9a5f-9d10d6c2caca","sectionTitle":"Where it begins","linkId":"51f7bf45-c232-4c7b-b385-a277f5c43e2d"},{"uid":"f57967f4-3ea2-4ca5-a542-a22bbbd5e177","sectionTitle":"Explore banner","linkId":"ca13b155-249b-4659-bf3b-77d0553834e9"},{"uid":"d5f510bc-a99c-4e82-9055-521fc2bbe839","sectionTitle":"Meet The Team","linkId":"b6e472f7-1ed9-4232-84fa-46164ca3e283"},{"uid":"323ca45f-91ae-4746-a79b-b1c4edb869d7","sectionTitle":"Target","linkId":"81f427fa-c75c-42c0-884b-230f2e33b3b5"},{"uid":"044cdbcc-d994-4a19-b57c-b6f7ad486124","sectionTitle":"Roadmap","linkId":"29e34e14-7811-464f-8737-844c38136ee5"},{"uid":"35f03aa9-d87c-444c-a1b6-2d8871dc1473","sectionTitle":"Footer","linkId":"0e484fc4-a8a4-4781-b829-e616dffd12fa"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"1a6b784e-75b4-4080-ba57-221e96b56182","linkId":"1a6b784e-75b4-4080-ba57-221e96b56182","name":"P - Compare Account","folder":null,"paths":{"en":"compare-account","default":"compare-account"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"a66320b0-45c7-400d-bbfb-ca0f2f9c915d","sectionTitle":"Top Nav","linkId":"71587091-2192-4f02-91cb-a82e6728919a"},{"uid":"697f0835-e072-4bf4-ad78-cbb651706294","sectionTitle":"Hero","linkId":"9ef35738-1e3f-4c1b-877a-c4be3f2d1fe1"},{"uid":"26b16115-455e-4f5b-bb8b-a95bb77b4e7a","sectionTitle":"Compare Table","linkId":"25505f80-2d98-4703-b239-bc0d889636b6"},{"uid":"2eb5cbde-7e4a-4f6a-81cc-3d70158debbc","sectionTitle":"Footer","linkId":"e1b53b41-6b31-4a64-8193-a9540c65f4cb"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"91979a7f-3ae4-4982-ab72-c4678ddce574","linkId":"91979a7f-3ae4-4982-ab72-c4678ddce574","name":"P - Compare Fund","folder":null,"paths":{"en":"compare-fund","default":"compare-fund"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"c5e73cbd-57f7-48a5-8999-47bd997e59cb","sectionTitle":"Top Nav","linkId":"e7bb2780-6558-46ca-b936-0f804e36c188"},{"uid":"74fa8989-ab3d-4810-9177-14a3ee2199a7","sectionTitle":"Hero","linkId":"7dd84c7f-d546-4c78-af8a-0c9869781d69"},{"uid":"10575424-2661-48d8-a932-b5d36a145e00","sectionTitle":"Compare Table","linkId":"a597111e-d369-4c0b-ac0d-f99dfd5b44e9"},{"uid":"ba031612-78d8-48b1-9d34-d5e1845d901b","sectionTitle":"Footer","linkId":"047452c0-bfc0-465e-b622-703e7b0004b1"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""},{"id":"3aabf671-1f7f-4e71-8fc5-afa03200a916","linkId":"3aabf671-1f7f-4e71-8fc5-afa03200a916","name":"S - Visual","folder":null,"paths":{"en":"visual","default":"visual"},"langs":["en"],"cmsDataSetPath":null,"sections":[{"uid":"74a247a9-094a-4f40-9462-e2df9d5ec58f","sectionTitle":"Top Nav","linkId":"f98b240e-ba94-4cfc-9816-6dc8fde53802"},{"uid":"9dfe3033-46ea-4115-a374-c2415ff9718b","sectionTitle":"Header","linkId":"d955985d-f3d4-4d65-9887-9ae410d06490"},{"uid":"611f2919-2534-4394-b9a9-56d9eb0e7791","sectionTitle":"Image","linkId":"a585c005-bf7a-4bc1-9d8e-be6e70037735"},{"uid":"3ab24e4b-6104-4c1f-bfe1-277acfc3760d","sectionTitle":"Image 2","linkId":"e4b961db-2888-4837-9693-d3ae5bfd0cbb"},{"uid":"6ced93a8-6923-451f-94fa-36c8a04f3691","sectionTitle":"More","linkId":"ccbdad54-550b-422a-a482-6a6bd2b70063"},{"uid":"6cf66cc0-f1ff-470b-8c1a-fe1073f18d0f","sectionTitle":"Footer","linkId":"aab74629-75e7-4399-9962-31b08f5f6217"}],"pageUserGroups":[],"title":{"en":"","fr":"Vide | Commencer à partir de zéro"},"meta":{"desc":{},"keywords":{},"socialDesc":{},"socialTitle":{},"structuredData":{}},"metaImage":""}],"plugins":[{"id":"f9ef41c3-1c53-4857-855b-f2f6a40b7186","name":"Supabase","namespace":"supabase"},{"id":"615d0fde-2084-4ac2-a2a4-5eb7686446ce","name":"RSS Feed","namespace":"fluxRSS"},{"id":"9c40819b-4a8f-468f-9ba5-4b9699f3361f","name":"Charts","namespace":"chartjs"},{"id":"2bd1c688-31c5-443e-ae25-59aa5b6431fb","name":"REST API","namespace":"restApi"}]};
// eslint-disable-next-line no-undef
window.wwg_cacheVersion = 22;
// eslint-disable-next-line no-undef
window.wwg_pluginsSettings = pluginsSettings;
// eslint-disable-next-line no-undef
window.wwg_disableManifest = false;

const defaultLang = window.wwg_designInfo.langs.find(({ default: isDefault }) => isDefault) || {};

const registerRoute = (page, lang, forcedPath) => {
    const langSlug = !lang.default || lang.isDefaultPath ? `/${lang.lang}` : '';
    let path =
        forcedPath ||
        (page.id === window.wwg_designInfo.homePageId ? '/' : `/${page.paths[lang.lang] || page.paths.default}`);

    //Replace params
    path = path.replace(/{{([\w]+)\|([^/]+)?}}/g, ':$1');

    routes.push({
        path: langSlug + path,
        component: wwPage,
        name: `page-${page.id}-${lang.lang}`,
        meta: {
            pageId: page.id,
            lang,
            isPrivate: !!page.pageUserGroups?.length,
        },
        async beforeEnter(to, from) {
            if (to.name === from.name) return;
            //Set page lang
            wwLib.wwLang.defaultLang = defaultLang.lang;
            wwLib.$store.dispatch('front/setLang', lang.lang);

            //Init plugins
            await initializePlugins();

            //Check if private page
            if (page.pageUserGroups?.length) {
                // cancel navigation if no plugin
                if (!wwLib.wwAuth.plugin) {
                    return false;
                }

                await wwLib.wwAuth.init();

                // Redirect to not sign in page if not logged
                if (!wwLib.wwAuth.getIsAuthenticated()) {
                    window.location.href = `${wwLib.wwPageHelper.getPagePath(
                        wwLib.wwAuth.getUnauthenticatedPageId()
                    )}?_source=${to.path}`;

                    return null;
                }

                //Check roles are required
                if (
                    page.pageUserGroups.length > 1 &&
                    !wwLib.wwAuth.matchUserGroups(page.pageUserGroups.map(({ userGroup }) => userGroup))
                ) {
                    window.location.href = `${wwLib.wwPageHelper.getPagePath(
                        wwLib.wwAuth.getUnauthorizedPageId()
                    )}?_source=${to.path}`;

                    return null;
                }
            }

            try {
                await import(`@/pages/${page.id.split('_')[0]}.js`);
                await wwLib.wwWebsiteData.fetchPage(page.id);

                //Scroll to section or on top after page change
                if (to.hash) {
                    const targetElement = document.getElementById(to.hash.replace('#', ''));
                    if (targetElement) targetElement.scrollIntoView();
                } else {
                    document.body.scrollTop = document.documentElement.scrollTop = 0;
                }

                return;
            } catch (err) {
                wwLib.$store.dispatch('front/showPageLoadProgress', false);

                if (err.redirectUrl) {
                    return { path: err.redirectUrl || '404' };
                } else {
                    //Any other error: go to target page using window.location
                    window.location = to.fullPath;
                }
            }
        },
    });
};

for (const page of window.wwg_designInfo.pages) {
    for (const lang of window.wwg_designInfo.langs) {
        if (!page.langs.includes(lang.lang)) continue;
        registerRoute(page, lang);
    }
}

const page404 = window.wwg_designInfo.pages.find(page => page.paths.default === '404');
if (page404) {
    for (const lang of window.wwg_designInfo.langs) {
        // Create routes /:lang/:pathMatch(.*)* etc for all langs of the 404 page
        if (!page404.langs.includes(lang.lang)) continue;
        registerRoute(
            page404,
            {
                default: false,
                lang: lang.lang,
            },
            '/:pathMatch(.*)*'
        );
    }
    // Create route /:pathMatch(.*)* using default project lang
    registerRoute(page404, { default: true, isDefaultPath: false, lang: defaultLang.lang }, '/:pathMatch(.*)*');
} else {
    routes.push({
        path: '/:pathMatch(.*)*',
        async beforeEnter() {
            window.location.href = '/404';
        },
    });
}

let routerOptions = {};

const isProd =
    !window.location.host.includes(
        // TODO: add staging2 ?
        '-staging.' + (process.env.WW_ENV === 'staging' ? import.meta.env.VITE_APP_PREVIEW_URL : '')
    ) && !window.location.host.includes(import.meta.env.VITE_APP_PREVIEW_URL);

if (isProd && window.wwg_designInfo.baseTag?.href) {
    let baseTag = window.wwg_designInfo.baseTag.href;
    if (!baseTag.startsWith('/')) {
        baseTag = '/' + baseTag;
    }
    if (!baseTag.endsWith('/')) {
        baseTag += '/';
    }

    routerOptions = {
        base: baseTag,
        history: createWebHistory(baseTag),
        routes,
    };
} else {
    routerOptions = {
        history: createWebHistory(),
        routes,
    };
}

router = createRouter({
    ...routerOptions,
    scrollBehavior,
});

//Trigger on page unload
let isFirstNavigation = true;
router.beforeEach(async (to, from) => {
    if (to.name === from.name) return;
    if (!isFirstNavigation) await onPageUnload();
    isFirstNavigation = false;
    wwLib.globalVariables._navigationId++;
    return;
});

//Init page
router.afterEach((to, from, failure) => {
    wwLib.$store.dispatch('front/showPageLoadProgress', false);
    let fromPath = from.path;
    let toPath = to.path;
    if (!fromPath.endsWith('/')) fromPath = fromPath + '/';
    if (!toPath.endsWith('/')) toPath = toPath + '/';
    if (failure || (from.name && toPath === fromPath)) return;
    initializeData(to);
});
/* wwFront:end */

export default router;
