const fs = require('fs');
const path = require('path');

const root = process.cwd();
const homePath = path.join(root, 'public/data/d4f7588a-fc42-4971-8154-7c897875ee5d.json');
const aboutPath = path.join(root, 'public/data/db8ea4e1-9fd0-48d3-86ea-e33c4017f636.json');
const viteConfigPath = path.join(root, 'vite.config.js');

function readJson(filePath) {
    return JSON.parse(fs.readFileSync(filePath, 'utf8'));
}

function writeJson(filePath, value) {
    fs.writeFileSync(filePath, JSON.stringify(value));
}

function setText(target, id, text) {
    const node = target.wwObjects[id];
    if (!node?.content?.default?.['_ww-text_text']) return;
    node.content.default['_ww-text_text'].en = `<div>${text}</div>`;
}

function setRichText(target, id, html) {
    const node = target.wwObjects[id];
    if (!node?.content?.default?.['_ww-text_text']) return;
    node.content.default['_ww-text_text'].en = html;
}

function replaceTextExact(target, from, to) {
    for (const node of Object.values(target.wwObjects || {})) {
        const textEntry = node?.content?.default?.['_ww-text_text'];
        if (!textEntry?.en) continue;
        if (textEntry.en === from) {
            textEntry.en = to;
        }
    }
}

const home = readJson(homePath);

setText(home, 'f3895715-f6af-4fd6-bbd4-4aabb313303d', 'Singapore RES prep for Paper 1 and Paper 2');
setText(
    home,
    '4b0d3a32-bd4c-4d5e-a88e-53c35e3b0f87',
    'Revise smarter with focused notes, topic breakdowns, and practice questions for the Real Estate Salesperson exam in Singapore.'
);
setText(
    home,
    'e6eb1f9b-06b0-427f-8480-52e02a3ed851',
    'Cover estate agency law, the Code of Ethics, HDB rules, private housing, contracts, financing, and transaction workflows in one study hub.'
);

setText(home, '8d96b659-c1e0-4836-b2fd-b4c28a5faef5', 'Paper 1');
setText(home, '2a7d11ce-8444-4b9d-a025-3966fc9b8a69', 'Estate agency, law, and ethics');
setText(home, 'a7abe559-5a8d-47ce-84d8-734d948228ac', 'Paper 2');
setText(home, 'c5e5fa11-7eaa-4fb0-a54a-05c70d70056b', 'Property practice and transactions');
setText(home, 'e0c4fa7e-aafe-485b-a228-3557efe8b84d', 'Mock Exams');
setText(home, '7b6353ff-17c7-4438-860b-5dd00458bc12', 'Timed drills and revision sets');

setText(home, '621bd495-5d24-425f-85c4-903f5f57e78f', 'Study Guides');
setText(home, '0185f1ac-7e63-43c2-bad6-549cf0698d17', 'Paper 1');
setText(home, '461c58be-b031-435a-91b5-ce8565df043e', 'Paper 2');
setText(home, '1cf5755a-4640-4ce3-8fef-7e99faca8d3f', 'Mock Exams');
setText(home, 'bbb7ac25-caf9-4656-9f2e-b303c1765fe6', 'Paper 1');
setText(home, '2fe4c0db-3a1b-4490-9912-8d1df743be14', 'Paper 2');
setText(home, '549d33e6-9fec-42da-bd9d-8823996b104b', 'Case Practice');

setText(home, '22b6926c-98f2-41fc-80b9-a2537fa36eb9', 'HDB Rules');
setText(home, '235e4253-c49f-49a0-b627-e154e85167e8', 'Private Housing');
setText(home, '40d94794-451c-4930-b9c1-dd9ada523b8e', 'Contracts');
setText(home, '538561d2-b662-4226-94a1-fc20250fa5a0', 'Financing');
setText(home, '568b7bae-364d-4bea-8000-b7ed939e5047', 'Exam Tips');
setText(home, 'f6448499-f2ad-4280-8741-7a20f518bc7d', 'Transactions');
setText(home, 'bfa33412-ee09-4359-914b-10252de84d8d', 'Start Paper 1');
setText(home, 'e5daaa3a-7e60-4581-99fe-476ac459ddd4', 'Start Paper 2');
setText(home, 'b709ec41-9596-4a6f-bd75-02215c4e01b2', 'View Topic List');

setText(home, 'cf101d31-3467-4a72-8d73-c3c0c91eed62', 'Paper 1');
setText(home, 'd601c61c-421a-4d78-a22d-54e393ec1cb8', 'Estate agency law');
setText(home, 'e53e776f-41da-4dc0-910c-edc31e066cc4', 'HDB rules');
setText(home, 'e19e98d9-cf43-4a26-adf5-ba1aaa8e84d2', 'Agency practice');
setText(home, '19d072bb-c727-49cd-9ba5-38557087b5ad', 'Private housing');
setText(home, '9abf8bc4-671e-45b7-ad5d-a8167555df78', 'Paper 2');
setText(home, 'f5c9aca4-b6d9-4880-94ea-aa68bbe58be0', 'Property transactions');
setText(home, '73540332-f2a0-480e-988b-5e46db5fa365', 'Ethics and CEA rules');
setText(home, '3220d75e-abb1-4292-9267-d512c689698f', 'Sale and purchase');
setText(home, 'fa51b808-0281-4ce7-b862-9e4304479a9c', 'Financing');
setText(home, 'd707fca0-19f9-4bcc-bf35-1d8951694535', 'RES Prep Hub');
setText(home, 'd72e1f85-ff4b-4bd1-b16d-b927bc4540b6', 'RES Prep Hub');
setText(home, '13535197-132c-4bcd-9f48-14e9961793ae', 'Practice Sets');
setRichText(
    home,
    '022432ee-2fab-4454-8eb5-584531cfb9aa',
    '<div>This site is designed for learners preparing for the Singapore Real Estate Salesperson examination. Use it to review Paper 1 and Paper 2 topics, strengthen weak areas, and build exam confidence with structured revision.</div>'
);

setText(home, 'a3638d9a-72e2-4609-9738-d508b635b5fa', 'Paper 1');
setText(home, '9d161e16-c9ff-4a52-82ff-cf94f1d9de56', 'CEA framework');
setText(home, '9e5cc50d-bffa-4de9-bc1d-d2ab8e876a8f', 'Code of Ethics');
setText(home, 'a3ecbbff-6dc0-4282-a1f5-fb08af659b39', 'Estate agency law');
setText(home, 'b3ef9484-57d0-4dc5-9602-5fc86834df0b', 'Paper 2');
setText(home, '02a0db52-4731-4b40-84e4-958e316f159d', 'HDB resale');
setText(home, '1e8f0603-4585-47f3-b79e-05a95a798974', 'Sale process');
setText(home, '6231d554-0491-4416-92de-16f311e28916', 'Financing and stamp duties');
setText(home, 'a4ef2f55-45c4-4d25-b3c9-e4abe91fffb2', 'Tenancy and forms');

setText(home, '5b662525-8456-449c-bd9a-58539f8b68d2', 'Paper 1');
setText(home, '74ded5ea-9283-480d-ac72-e6780c7f1fe0', 'Paper 2');
setText(home, 'b7c7907a-b678-4c18-98fa-d74e6a7a7d9d', 'Paper 2');
setText(home, 'c9528004-3df8-47ce-a054-eedf8de88b94', 'Topics');
setText(home, 'd013af15-3995-4abb-a885-827788061578', 'Mock exams');
setText(home, 'e15cc84c-bd63-4051-b7c7-e963384c0af4', 'Paper 1 checklist');
setRichText(
    home,
    'e2cc4d01-79ad-45f4-b8ec-88641e442e5f',
    '<div>Disclaimer: This is an independent study resource for Singapore Real Estate Salesperson exam preparation. It is not affiliated with or endorsed by CEA. Always cross-check important rules and requirements against the latest official materials.</div>'
);
setText(home, 'e32ea5b6-c42f-47c9-9b15-8fe36801eebb', '© 2026 RES Ace Singapore. All Rights Reserved.');

writeJson(homePath, home);

const about = readJson(aboutPath);
setText(about, 'd707fca0-19f9-4bcc-bf35-1d8951694535', 'About RES Ace Singapore');
setRichText(about, '987fd004-ff0b-492a-b31f-3b8ea98cfb0c', '<div>Singapore RES</div><div>Study Hub</div>');
setRichText(
    about,
    '84abd461-efdb-462d-9757-e4bb982b0222',
    '<div>RES Ace Singapore is built around clear explanations, practical exam coverage, and structured revision support for serious RES candidates.</div>'
);
setRichText(
    about,
    '2ea47b91-4661-49cd-bdfe-18b7063c959f',
    '<div>Preparing for the RES exam can feel scattered and overwhelming. This site repackages key topics into a cleaner revision flow so learners can focus on understanding, recall, and exam technique.</div>'
);
setRichText(
    about,
    '022432ee-2fab-4454-8eb5-584531cfb9aa',
    '<div>RES Ace Singapore helps aspiring real estate salespersons prepare for the Singapore RES examination with clearer explanations, focused topic coverage, and practical revision support for both Paper 1 and Paper 2.</div>'
);

const sharedExactReplacements = [
    ['<div>Banking</div>', '<div>Paper 1</div>'],
    ['<div>Investment</div>', '<div>Paper 2</div>'],
    ['<div>Insurance</div>', '<div>Mock Exams</div>'],
    ['<div>Accounts</div>', '<div>HDB Rules</div>'],
    ['<div>ETF</div>', '<div>Private Housing</div>'],
    ['<div>Stocks and Share</div>', '<div>Contracts</div>'],
    ['<div>Stock and Share</div>', '<div>Sale and Purchase</div>'],
    ['<div>Funds</div>', '<div>Financing</div>'],
    ['<div>Cards</div>', '<div>Exam Tips</div>'],
    ['<div>Financing/ Loans</div>', '<div>Agency Practice</div>'],
    ['<div>Savings Account</div>', '<div>CEA Framework</div>'],
    ['<div>Fixed Deposit Account</div>', '<div>Code of Ethics</div>'],
    ['<div>Current Account</div>', '<div>Estate Agency Law</div>'],
    ['<div>Multi-Currency Account</div>', '<div>Mock Exams</div>'],
    ['<div>Exchange Traded Fund</div>', '<div>Paper 1 Checklist</div>'],
    ['<div>© 2025 RES Exam Bank. All Rights Reserved.</div>', '<div>© 2026 RES Ace Singapore. All Rights Reserved.</div>'],
    [
        '<div>Disclaimer: The content provided on this website is for informational purposes only and does not constitute financial, investment, legal, or tax advice. RES Exam Bank is not a financial advisor, broker, or dealer. The information presented is obtained from sources believed to be reliable, but we do not guarantee its accuracy, completeness, or timeliness. All financial decisions involve risk, and you should conduct your own research and consult with a qualified professional before making any financial decisions. RES Exam Bank is not liable for any losses or damages arising from the use of or reliance on the information provided on this site.</div>',
        '<div>Disclaimer: This is an independent study resource for Singapore Real Estate Salesperson exam preparation. It is not affiliated with or endorsed by CEA. Always cross-check important rules and requirements against the latest official materials.</div>',
    ],
];

const aboutExactReplacements = [
    ['<div>Your Financial Journey, Revolutionized.</div>', '<div>Your RES journey, made clearer.</div>'],
    [
        '<div>RES Exam Bank is a global financial discovery platform dedicated to empowering people everywhere with the tools and knowledge to make smart, confident financial decisions.</div>',
        '<div>RES Ace Singapore is a focused exam-prep platform built to help future real estate salespersons study smarter and approach the RES papers with confidence.</div>',
    ],
    [
        '<div>RES Exam Bank is powered by a dedicated team of finance professionals, tech innovators, and market analysts who are passionate about making finance accessible to everyone.</div>',
        '<div>RES Ace Singapore is built around clear explanations, practical exam coverage, and structured revision support for serious RES candidates.</div>',
    ],
    [
        '<div>RES Exam Bank is powered by a dedicated team of finance professionals, tech innovators, and market analysts who are passionate about making finance accessible to everyone. With decades of combined experience, we are united by a single mission: to empower you with the clarity and tools needed to navigate the financial world with confidence.</div>',
        '<div>Our goal is simple: make the Singapore RES syllabus easier to understand, easier to revise, and easier to apply under exam conditions for both Paper 1 and Paper 2.</div>',
    ],
    [
        '<div>Finding the right financial products used to mean endless searching and uncertainty. We knew there had to be a better way. RES Exam Bank was founded by finance professionals and industry experts to create a single, powerful platform that puts you in control.</div>',
        '<div>Preparing for the RES exam can feel scattered and overwhelming. This site repackages key topics into a cleaner revision flow so learners can focus on understanding, recall, and exam technique.</div>',
    ],
];

for (const [from, to] of sharedExactReplacements) {
    replaceTextExact(home, from, to);
    replaceTextExact(about, from, to);
}

for (const [from, to] of aboutExactReplacements) {
    replaceTextExact(about, from, to);
}

writeJson(aboutPath, about);

let viteConfig = fs.readFileSync(viteConfigPath, 'utf8');
viteConfig = viteConfig.replaceAll('RES Exam Bank', 'RES Ace Singapore | Paper 1 & Paper 2 Prep');
fs.writeFileSync(viteConfigPath, viteConfig);
