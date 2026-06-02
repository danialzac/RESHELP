package com.resace.backend;

import com.resace.backend.model.AnswerOption;
import com.resace.backend.model.Paper;
import com.resace.backend.model.Question;
import com.resace.backend.model.Topic;
import com.resace.backend.repository.QuestionRepository;
import com.resace.backend.repository.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds placeholder RES exam content on first startup.
 * Replace with real licensed content before public launch.
 * Each question carries framework-enriched learning fields:
 *   plainEnglish  – jargon-free explanation (≤100 words)
 *   memoryRule    – "If you see X, think Y" shortcut
 *   examTrap      – why students get this wrong
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        if (topicRepository.count() > 0) {
            log.info("Seed data already present — skipping initialization.");
            return;
        }
        log.info("Seeding RES Exam Bank placeholder content...");
        seedPaper1();
        seedPaper2();
        log.info("Seed complete.");
    }

    // ─────────────────────────────────────────────────────────── Paper 1 ──────

    private void seedPaper1() {
        Topic legislation = saveTopic("Real Estate Legislation", "p1-legislation", Paper.PAPER_1,
            "Key acts and regulations governing Singapore real estate transactions.");
        Topic ownership = saveTopic("Property Ownership & Tenure", "p1-ownership", Paper.PAPER_1,
            "Types of property ownership, tenure, and title in Singapore.");
        Topic market = saveTopic("Property Market Overview", "p1-market", Paper.PAPER_1,
            "Singapore property market structure, segments, and key data.");
        Topic housing = saveTopic("Housing Policies & HDB", "p1-housing", Paper.PAPER_1,
            "Government housing policies, HDB schemes, and eligibility rules.");
        Topic tax = saveTopic("Property Taxation", "p1-tax", Paper.PAPER_1,
            "Stamp duties, property tax, GST implications for real estate.");

        // ── Legislation ──────────────────────────────────────────────────────

        saveQ(legislation, Paper.PAPER_1,
            "Under the Estate Agents Act, which body is responsible for licensing real estate agencies in Singapore?",
            "MEDIUM", "HIGH",
            "The Council for Estate Agencies (CEA) was established under the Estate Agents Act 2010 to regulate the real estate agency industry in Singapore. It licenses estate agents and salespersons.",
            "The Estate Agents Act created a government body called CEA to be the 'policeman' for real estate agents in Singapore. CEA decides who gets a licence to work as an agent or salesperson, and it can take action if someone breaks the rules.",
            "If you see Estate Agents Act → think CEA. Not HDB, not URA, not MAS. CEA is the only body that licenses agents.",
            "Students pick HDB (it handles housing) or URA (it handles planning). Neither licenses agents. CEA is the dedicated regulator.",
            new String[][]{
                {"A", "Housing Development Board (HDB)", "false"},
                {"B", "Council for Estate Agencies (CEA)", "true"},
                {"C", "Urban Redevelopment Authority (URA)", "false"},
                {"D", "Monetary Authority of Singapore (MAS)", "false"}
            });

        saveQ(legislation, Paper.PAPER_1,
            "Which of the following is NOT a requirement under the Estate Agents Act for a licensed salesperson?",
            "MEDIUM", "HIGH",
            "The Estate Agents Act requires salespersons to pass the RES examination, be registered with CEA, and be employed by a licensed estate agent. There is no requirement to hold a university degree.",
            "To become a licensed RES salesperson you need to: pass the RES exam, register with CEA, and be employed by a licensed agency. A university degree is NOT required — many successful agents don't have one.",
            "If the question says NOT a requirement → look for the odd one out. Exam + CEA + employment = YES. University degree = NO.",
            "Students assume 'professions need degrees' and skip option C. The trick is the word NOT — the degree requirement is the one that doesn't exist.",
            new String[][]{
                {"A", "Pass the RES examination", "false"},
                {"B", "Be registered with CEA", "false"},
                {"C", "Hold a university degree", "true"},
                {"D", "Be employed by a licensed estate agent", "false"}
            });

        saveQ(legislation, Paper.PAPER_1,
            "The Conveyancing and Law of Property Act (Cap. 61) primarily governs which of the following?",
            "HARD", "MEDIUM",
            "The Conveyancing and Law of Property Act governs conveyancing (the transfer of property) and related property law matters such as the creation, transfer, and enforcement of property interests.",
            "The Conveyancing and Law of Property Act is the big rulebook for how property changes hands in Singapore — things like buying, selling, and registering ownership. 'Conveyancing' literally means the process of transferring property.",
            "Conveyancing = transferring property. Cap. 61 = rules for how property changes hands. The name tells you the answer.",
            "Students confuse this with licensing laws (Estate Agents Act) or tax laws. The word 'conveyancing' is your clue — it means the process of property transfer.",
            new String[][]{
                {"A", "The licensing of property agents", "false"},
                {"B", "The transfer and ownership of real property", "true"},
                {"C", "The valuation of properties", "false"},
                {"D", "The taxation of property transactions", "false"}
            });

        saveQ(legislation, Paper.PAPER_1,
            "Under the Land Titles Act, what is the effect of registration of a transfer of land?",
            "HARD", "MEDIUM",
            "Under the Torrens system (Land Titles Act), registration of a transfer confers indefeasible title on the registered proprietor, meaning the title cannot be defeated except in limited circumstances like fraud.",
            "Singapore uses the Torrens system. Once you register a property transfer, the new owner gets 'indefeasible title' — meaning almost no one can take that ownership away from them. Registration = bulletproof ownership.",
            "Register → Indefeasible title. Registration is the trigger, not signing. The moment it's registered, you're fully protected.",
            "Students pick 'effective from date of signing' — natural instinct. But under the Torrens system, REGISTRATION is the trigger, not signing.",
            new String[][]{
                {"A", "The transfer becomes effective from the date of signing", "false"},
                {"B", "The transferee gains indefeasible title upon registration", "true"},
                {"C", "The transfer is only effective after stamp duty payment", "false"},
                {"D", "The previous owner retains a right of first refusal", "false"}
            });

        saveQ(legislation, Paper.PAPER_1,
            "A salesperson who acts for both the buyer and seller in the same transaction without consent is committing what kind of breach?",
            "EASY", "HIGH",
            "Acting for both buyer and seller in the same transaction without proper disclosure and consent creates a conflict of interest. This is prohibited under CEA's Code of Ethics and Professional Client Care.",
            "If an agent secretly represents both the buyer AND the seller without telling both parties, that's a conflict of interest — like a referee who plays on both teams. You can't truly serve two opposing sides.",
            "One agent + both sides + no consent = Conflict of Interest. Always.",
            "Students confuse this with stamp duty breaches or CPD issues. It's clearly about serving two masters without consent — that's always conflict of interest.",
            new String[][]{
                {"A", "A breach of stamp duty requirements", "false"},
                {"B", "A conflict of interest breach", "true"},
                {"C", "A CPD requirement breach", "false"},
                {"D", "A tenancy agreement breach", "false"}
            });

        // ── Ownership ────────────────────────────────────────────────────────

        saveQ(ownership, Paper.PAPER_1,
            "Which type of property tenure in Singapore means the owner holds the property forever with no time limit?",
            "EASY", "HIGH",
            "Freehold tenure means the owner holds the property indefinitely with no expiry. In Singapore, freehold properties are typically described as 999-year leasehold or estate in fee simple.",
            "Freehold = own forever, no expiry. 99-year leasehold = own until the time runs out. Freehold is the only option with no countdown clock.",
            "Free-HOLD = hold forever. Lease-HOLD = hold until time runs out. The word 'free' = no restrictions = forever.",
            "Students sometimes pick 99-year leasehold confusing it with 'a long time.' But 99 years IS leasehold — it has an end date. Freehold is the only one with no expiry.",
            new String[][]{
                {"A", "99-year leasehold", "false"},
                {"B", "Freehold", "true"},
                {"C", "30-year leasehold", "false"},
                {"D", "Tenancy at will", "false"}
            });

        saveQ(ownership, Paper.PAPER_1,
            "In a joint tenancy, what happens to a deceased owner's share of the property?",
            "MEDIUM", "HIGH",
            "In a joint tenancy, the right of survivorship applies. Upon the death of one joint tenant, their share automatically passes to the surviving joint tenants — it does not form part of the deceased's estate.",
            "In a joint tenancy, when one owner dies, their share doesn't go to their family or follow their will. It automatically jumps to the surviving co-owners. This is called the 'right of survivorship.' Your will cannot override this.",
            "Joint tenancy = survivor takes all. Death → share jumps to the others automatically. Your will is irrelevant for jointly-held property.",
            "Students pick 'passes according to will' because that's how inheritance normally works. But joint tenancy overrides the will — the right of survivorship is automatic.",
            new String[][]{
                {"A", "It passes according to the deceased's will", "false"},
                {"B", "It passes automatically to the surviving joint tenant(s)", "true"},
                {"C", "It reverts to the government", "false"},
                {"D", "It is divided equally among all heirs", "false"}
            });

        saveQ(ownership, Paper.PAPER_1,
            "What distinguishes tenancy-in-common from joint tenancy?",
            "MEDIUM", "HIGH",
            "In tenancy-in-common, each owner holds a defined (often unequal) share and can dispose of their share independently, including through a will. There is no right of survivorship.",
            "In tenancy-in-common, each person owns a clear piece of the pie and can sell or pass their piece independently. In joint tenancy, everyone owns the whole thing together and shares can't be split separately.",
            "Tenancy-in-COMMON = each has their OWN share to dispose of. Joint = all together as one. Common = individual shares. Joint = no individual shares.",
            "Option A says 'equally' — but tenancy-in-common allows unequal shares (e.g. 70-30). This 'equal' distractor catches students who assume common ownership means equal shares.",
            new String[][]{
                {"A", "Tenancy-in-common owners share the property equally", "false"},
                {"B", "Each tenant-in-common owns a distinct share and can transfer it independently", "true"},
                {"C", "Tenancy-in-common is only available for commercial properties", "false"},
                {"D", "Tenancy-in-common requires government approval to transfer shares", "false"}
            });

        saveQ(ownership, Paper.PAPER_1,
            "A 99-year leasehold flat with 30 years remaining on the lease — what is the main concern for a buyer?",
            "MEDIUM", "HIGH",
            "Properties with shorter remaining lease terms face financing constraints (banks may reduce loan quantum), lower resale value, and CPF usage restrictions. A 30-year remaining lease would significantly affect CPF usage eligibility.",
            "When a leasehold property has only 30 years left, banks get nervous and lend less money on it, and your CPF (retirement savings) has limits on how much can be used. Short lease = short on financing options.",
            "Short lease (<60 years) → CPF restrictions + lower bank loans. The shorter the lease, the harder it is to finance.",
            "Option A says 'cannot get mortgage for any leasehold property' — too extreme and wrong. Banks DO lend on leasehold. Beware of absolute words like 'cannot' — they're usually wrong.",
            new String[][]{
                {"A", "The buyer cannot get a mortgage for any leasehold property", "false"},
                {"B", "Financing and CPF usage may be restricted due to the short remaining lease", "true"},
                {"C", "The government automatically renews the lease at no cost", "false"},
                {"D", "Leasehold properties cannot be sold in the last 40 years of the lease", "false"}
            });

        saveQ(ownership, Paper.PAPER_1,
            "Strata title is most commonly associated with which type of property?",
            "EASY", "MEDIUM",
            "Strata title applies to properties within a larger development where the building is subdivided into separate units. Condominiums and apartments are the most common strata-titled properties in Singapore.",
            "Strata title is the ownership system for your unit inside a shared building — like a condo. You own your unit plus a share of common areas. Landed houses on their own land use a different system.",
            "Strata = shared building, individual unit. Think condos and apartments. Not landed houses.",
            "Students sometimes pick shophouses or landed properties. Remember: strata title is specifically for units WITHIN a larger shared development.",
            new String[][]{
                {"A", "Landed houses on individual land plots", "false"},
                {"B", "Condominiums and apartments", "true"},
                {"C", "Shophouses with no residential component", "false"},
                {"D", "Government-owned industrial facilities", "false"}
            });

        // ── Market ───────────────────────────────────────────────────────────

        saveQ(market, Paper.PAPER_1,
            "Which government agency is primarily responsible for releasing the Singapore Property Price Index (PPI)?",
            "MEDIUM", "HIGH",
            "The Urban Redevelopment Authority (URA) releases the private residential property price index (PPI) each quarter. It is a key indicator of private property market performance in Singapore.",
            "The PPI is a quarterly report card for private home prices. URA publishes it. CEA handles agent licences, HDB handles public housing — URA handles private market data.",
            "PPI = URA's job. Private market data = URA. HDB publishes HDB data; URA publishes private data.",
            "HDB is a very common wrong answer because HDB does publish flat data — but that's the HDB Resale Price Index, not the private PPI. Two separate indexes for two separate markets.",
            new String[][]{
                {"A", "Housing Development Board (HDB)", "false"},
                {"B", "Urban Redevelopment Authority (URA)", "true"},
                {"C", "Council for Estate Agencies (CEA)", "false"},
                {"D", "Building and Construction Authority (BCA)", "false"}
            });

        saveQ(market, Paper.PAPER_1,
            "The OCR, RCR, and CCR classifications are used to segment which type of Singapore property market?",
            "EASY", "HIGH",
            "OCR (Outside Central Region), RCR (Rest of Central Region), and CCR (Core Central Region) are geographic classifications used for the private residential property market in Singapore.",
            "CCR = prime areas. RCR = city fringe. OCR = suburbs. These three zones are only used to describe the PRIVATE RESIDENTIAL market — not HDB, not commercial.",
            "CCR/RCR/OCR = private residential ONLY. Think: postal districts translated into market zones.",
            "Students sometimes pick industrial/commercial or HDB. These zones don't apply to those. They're purely private residential market classifications.",
            new String[][]{
                {"A", "Industrial and commercial property", "false"},
                {"B", "Private residential property", "true"},
                {"C", "HDB public housing", "false"},
                {"D", "Strata commercial property only", "false"}
            });

        saveQ(market, Paper.PAPER_1,
            "What does the Total Debt Servicing Ratio (TDSR) framework govern in Singapore?",
            "MEDIUM", "HIGH",
            "The TDSR framework, introduced by MAS, limits the proportion of a borrower's gross monthly income that can be used to service all debt obligations, including the property loan being applied for. It is set at 55%.",
            "TDSR caps how much of your monthly income can go towards paying ALL your debts combined. If you earn $5,000/month, you can't spend more than $2,750/month (55%) on all loans together — including your home loan.",
            "TDSR = Total Debt cap = 55% of gross income. TDSR is about affordability, not ownership limits.",
            "Option A (max number of properties) — that's a different concept entirely. Option C (minimum holding period) describes SSD. These classic concept-confusion traps all appear in the wrong options.",
            new String[][]{
                {"A", "The maximum number of properties a buyer can own", "false"},
                {"B", "The amount of debt a borrower can service relative to income", "true"},
                {"C", "The minimum holding period before a property can be resold", "false"},
                {"D", "The stamp duty rates applicable on property purchases", "false"}
            });

        saveQ(market, Paper.PAPER_1,
            "Which of the following best describes the Seller's Stamp Duty (SSD) in Singapore?",
            "MEDIUM", "HIGH",
            "Seller's Stamp Duty (SSD) is a tax imposed on sellers who dispose of residential property within a certain holding period. It was introduced to discourage short-term speculative flipping of properties.",
            "SSD is a tax you pay if you sell your home too quickly after buying it. It was created to stop people from buying and flipping homes for quick profit. The faster you sell, the higher the tax.",
            "SSD = SELLER pays, SHORT holding period, RESIDENTIAL only. Sell too fast = pay SSD.",
            "Students confuse SSD with ABSD (buyer pays) and BSD (buyer pays). Remember: SSD is the SELLER's tax, only triggered by selling within a short holding period.",
            new String[][]{
                {"A", "A tax paid by buyers on all property purchases", "false"},
                {"B", "A tax paid by sellers who sell within a specified holding period", "true"},
                {"C", "A tax applied only to commercial properties", "false"},
                {"D", "A recurring annual tax on property ownership", "false"}
            });

        saveQ(market, Paper.PAPER_1,
            "What is the primary purpose of the Additional Buyer's Stamp Duty (ABSD)?",
            "EASY", "HIGH",
            "ABSD was introduced as a cooling measure to moderate investment demand and keep Singapore property prices sustainable. It applies additional stamp duty on top of BSD, with rates varying based on buyer profile and number of properties owned.",
            "ABSD is an extra tax on top of normal stamp duty when buying a home. How much you pay depends on who you are (SC/PR/Foreigner) and how many properties you own. It slows down speculation.",
            "ABSD = cooling measure = on top of BSD. More properties = higher ABSD. It's a market temperature control.",
            "Option C says 'replaces BSD' — completely wrong. ABSD is ON TOP of BSD, never instead of it. Students mix up the two separate stamp duties.",
            new String[][]{
                {"A", "To fund public housing construction", "false"},
                {"B", "To cool the property market and manage investment demand", "true"},
                {"C", "To replace the existing Buyer's Stamp Duty framework", "false"},
                {"D", "To penalise foreign developers", "false"}
            });

        // ── Housing Policies ─────────────────────────────────────────────────

        saveQ(housing, Paper.PAPER_1,
            "Which scheme allows eligible first-time HDB flat buyers to receive a cash grant for purchasing a resale flat?",
            "EASY", "HIGH",
            "The CPF Housing Grant (CHG) provides eligible first-time buyers with a cash grant when purchasing resale HDB flats. The grant amount varies based on income and flat type.",
            "CPF Housing Grant = cash grant from government to help first-timers buy a resale HDB flat. More grant for lower-income households. HIP is for upgrading old flats, SERS is for en bloc redevelopment.",
            "CHG = Cash grant for first-timers buying resale HDB. If you see 'cash grant + resale HDB' → think CPF Housing Grant.",
            "HIP (Home Improvement Programme) is a common wrong pick — it's about upgrading aging flats, not buying them. Students mix up the acronyms because they all relate to HDB.",
            new String[][]{
                {"A", "Home Improvement Programme (HIP)", "false"},
                {"B", "CPF Housing Grant (CHG)", "true"},
                {"C", "Selective En Bloc Redevelopment Scheme (SERS)", "false"},
                {"D", "Proximity Housing Grant (PHG)", "false"}
            });

        saveQ(housing, Paper.PAPER_1,
            "What is the Minimum Occupation Period (MOP) for a standard HDB Build-To-Order (BTO) flat?",
            "EASY", "HIGH",
            "The MOP for most HDB BTO flats is 5 years from the date the flat is collected (date of key collection or Temporary Occupation Permit). During this period, owners cannot sell or rent out the entire flat.",
            "After collecting your HDB BTO keys, you must live in the flat for at least 5 years before selling. This 5-year MOP stops people from buying HDB flats just to flip them for profit.",
            "HDB MOP = 5 years. Five-five-five. Stay 5 years before you can sell. Not 2, not 3 — always 5.",
            "Common wrong answers are 2 or 3 years. Some students confuse MOP with private property holding periods. The standard HDB BTO MOP is always 5 years.",
            new String[][]{
                {"A", "2 years", "false"},
                {"B", "3 years", "false"},
                {"C", "5 years", "true"},
                {"D", "10 years", "false"}
            });

        saveQ(housing, Paper.PAPER_1,
            "Under the Ethnic Integration Policy (EIP), what is the primary objective?",
            "MEDIUM", "MEDIUM",
            "The Ethnic Integration Policy (EIP) ensures that HDB estates maintain a balanced ethnic mix, preventing the formation of ethnic enclaves and promoting social cohesion in public housing.",
            "EIP sets quotas so no single ethnic group takes up too many flats in one HDB block or neighbourhood. The goal is racial balance — Singapore's public housing stays mixed and integrated.",
            "EIP = Ethnic quotas in HDB = social cohesion. EIP = keep neighbourhoods racially mixed.",
            "Option A (citizens only) describes a different eligibility rule. Option D (foreign nationals) is wrong — EIP is about ethnicity balance between Singapore residents, not citizenship.",
            new String[][]{
                {"A", "To ensure only Singaporean citizens can purchase HDB flats", "false"},
                {"B", "To maintain a balanced ethnic mix in HDB estates", "true"},
                {"C", "To prioritise first-time buyers over investors", "false"},
                {"D", "To limit the number of foreign nationals in each block", "false"}
            });

        saveQ(housing, Paper.PAPER_1,
            "A Singapore Permanent Resident (PR) who wishes to buy a new HDB flat directly from HDB — is this possible?",
            "MEDIUM", "HIGH",
            "Only Singapore Citizens are eligible to purchase new HDB flats directly from HDB. Singapore PRs can only purchase resale HDB flats from the open market, subject to eligibility conditions.",
            "Only Singapore Citizens can buy new BTO flats from HDB. PRs cannot — full stop. PRs can only buy resale HDB flats from other owners, and even then there are eligibility conditions.",
            "New HDB = Citizens ONLY. PRs = resale only. BTO = SC only. No exceptions.",
            "Options C and D offer false 'yes, but' conditions. The answer is simply NO. PRs cannot buy new HDB flats under any condition. Beware of partial-truth options that make you think there's an exception.",
            new String[][]{
                {"A", "Yes, PRs can buy BTO flats directly from HDB", "false"},
                {"B", "No, only Singapore Citizens can buy new HDB flats from HDB", "true"},
                {"C", "Yes, but only for 3-room flats and smaller", "false"},
                {"D", "Yes, after 5 years of PR status", "false"}
            });

        saveQ(housing, Paper.PAPER_1,
            "What does the DBSS (Design, Build and Sell Scheme) represent in Singapore's housing history?",
            "HARD", "LOW",
            "DBSS was a public housing scheme where private developers designed, built, and sold HDB flats. The scheme was discontinued in 2011 due to pricing concerns. DBSS flats are subject to HDB eligibility rules.",
            "DBSS was a government experiment: private developers built HDB-style public housing and sold it at their own prices. Prices got too high. The government ended DBSS in 2011. The flats still follow HDB resale rules.",
            "DBSS = private builder + public housing rules. Discontinued 2011. Not landed, not commercial, not a current scheme.",
            "Option A says 'landed housing' — wrong, DBSS was high-rise apartments. Option C says 'current scheme' — wrong, discontinued in 2011. Don't confuse DBSS with the current BTO programme.",
            new String[][]{
                {"A", "A scheme for private developers to build and sell landed housing", "false"},
                {"B", "A discontinued public housing scheme where private developers built HDB-classified flats", "true"},
                {"C", "A current scheme for mixed-use commercial and residential development", "false"},
                {"D", "A scheme for en bloc redevelopment of older HDB estates", "false"}
            });

        // ── Taxation ─────────────────────────────────────────────────────────

        saveQ(tax, Paper.PAPER_1,
            "Buyer's Stamp Duty (BSD) is calculated based on which value?",
            "EASY", "HIGH",
            "BSD is calculated on the higher of the purchase price or the market value of the property. This prevents understatement of purchase price to reduce stamp duty.",
            "When buying a property, BSD is based on whichever is higher — what you paid or what it's worth. This stops people from declaring a fake low price to pay less tax.",
            "BSD = higher of purchase price OR market value. Always take the bigger number. IRAS picks the higher one.",
            "Option D says 'lower of' — that's the trap. IRAS always takes the HIGHER value. Option A (loan amount) is completely irrelevant to stamp duty.",
            new String[][]{
                {"A", "The loan amount approved by the bank", "false"},
                {"B", "The higher of the purchase price or market value", "true"},
                {"C", "The assessed annual value as determined by IRAS", "false"},
                {"D", "The lower of the purchase price or market value", "false"}
            });

        saveQ(tax, Paper.PAPER_1,
            "Annual Value (AV) of a property is used as the basis for assessing which tax?",
            "EASY", "HIGH",
            "Annual Value (AV) is the estimated gross annual rental of a property if it were rented out. It is determined by IRAS and is the basis for computing property tax.",
            "IRAS estimates what your property could earn in rent per year — that's the Annual Value (AV). Even if you live in it yourself, you still have an AV. Your property tax is then calculated from this AV.",
            "AV → Property Tax. Annual Value = imaginary annual rent = the base for property tax. AV has nothing to do with BSD or ABSD.",
            "Students often pick BSD or ABSD because they're more commonly discussed. But AV is specifically linked to the RECURRING property tax, not one-time stamp duties.",
            new String[][]{
                {"A", "Buyer's Stamp Duty", "false"},
                {"B", "Property Tax", "true"},
                {"C", "Additional Buyer's Stamp Duty", "false"},
                {"D", "Goods and Services Tax on sale", "false"}
            });

        saveQ(tax, Paper.PAPER_1,
            "For residential properties in Singapore, what are the two different property tax rate structures?",
            "MEDIUM", "HIGH",
            "Residential properties in Singapore are subject to owner-occupier rates (lower, progressive) when the owner lives in the property, and non-owner-occupier rates (higher) for investment or rented-out properties.",
            "If you live in your home = lower owner-occupier tax rate. If you rent it out or leave it empty = higher non-owner-occupier rate. Singapore rewards owner-occupiers and taxes investors more.",
            "Live in it = owner-occupier (lower). Rent it out = non-owner-occupier (higher). Use it yourself = pay less.",
            "Options C (freehold vs leasehold) and D (citizen vs foreigner) are irrelevant — property tax rates are based on USAGE, not tenure or citizenship. Usage is the only thing that matters here.",
            new String[][]{
                {"A", "Corporate rate and individual rate", "false"},
                {"B", "Owner-occupier rate and non-owner-occupier rate", "true"},
                {"C", "Freehold rate and leasehold rate", "false"},
                {"D", "Citizen rate and foreigner rate", "false"}
            });

        saveQ(tax, Paper.PAPER_1,
            "Which of the following transactions would typically attract GST in Singapore?",
            "HARD", "MEDIUM",
            "Commercial property sales and rentals by GST-registered entities attract GST. Residential property sales and rentals are GST-exempt. An HDB shop unit sold by a GST-registered developer would be subject to GST.",
            "Residential property = no GST, ever. Commercial property from a GST-registered seller = GST applies. Remember: your home is GST-free. A shop unit is not.",
            "Residential = No GST. Commercial = GST (if seller is GST-registered). Home = free from GST. Shop = may have GST.",
            "Option A (selling own condo) is wrong — residential is GST-exempt. Option C (renting residential) is also exempt. The trap is mixing commercial and residential rules.",
            new String[][]{
                {"A", "A citizen selling their owner-occupied condominium unit", "false"},
                {"B", "Sale of a commercial shop unit by a GST-registered developer", "true"},
                {"C", "A landlord renting out a private residential apartment", "false"},
                {"D", "Transfer of an HDB flat between immediate family members", "false"}
            });

        saveQ(tax, Paper.PAPER_1,
            "Seller's Stamp Duty (SSD) for residential properties sold within the first year of purchase is charged at what rate?",
            "MEDIUM", "HIGH",
            "Under the current SSD framework (revised in 2023), the SSD rate for residential properties sold within 1 year of purchase is 12%. The rate decreases for each subsequent year, becoming nil after 3 years.",
            "If you buy a home and sell within year 1 → pay SSD at 12%. Year 2 = 8%. Year 3 = 4%. After 3 years = no SSD. Hold longer, pay less.",
            "SSD Year 1 = 12%. Year 2 = 8%. Year 3 = 4%. Year 4+ = 0%. Remember: 12-8-4-0.",
            "Students often guess 16% (too high) or 4% (confusing with later years). The specific number for year 1 is 12%. Memorise the 12-8-4-0 countdown.",
            new String[][]{
                {"A", "4%", "false"},
                {"B", "8%", "false"},
                {"C", "12%", "true"},
                {"D", "16%", "false"}
            });
    }

    // ─────────────────────────────────────────────────────────── Paper 2 ──────

    private void seedPaper2() {
        Topic agencyLaw = saveTopic("Property Agency Law", "p2-agency-law", Paper.PAPER_2,
            "Agency relationships, duties, and legal obligations for estate agents.");
        Topic marketing = saveTopic("Marketing & Property Listings", "p2-marketing", Paper.PAPER_2,
            "Property marketing regulations, listing agreements, and advertising rules.");
        Topic negotiation = saveTopic("Negotiation & Sales Process", "p2-negotiation", Paper.PAPER_2,
            "Sales process, negotiation techniques, and transaction documentation.");
        Topic ethics = saveTopic("Ethics & Professional Conduct", "p2-ethics", Paper.PAPER_2,
            "CEA Code of Ethics, professional responsibilities, and client duties.");
        Topic clientMgmt = saveTopic("Client Relationship Management", "p2-client", Paper.PAPER_2,
            "Client engagement, needs assessment, and long-term relationship building.");

        // ── Agency Law ───────────────────────────────────────────────────────

        saveQ(agencyLaw, Paper.PAPER_2,
            "In the context of real estate agency, who is the 'principal' in an agency relationship?",
            "EASY", "HIGH",
            "In an agency relationship, the principal is the party who appoints the agent to act on their behalf. In real estate, this is typically the seller (vendor) or landlord who engages the estate agent.",
            "The 'principal' is the person who hires the agent — usually the seller or landlord. The agent works FOR the principal. Think of it like a boss (principal) and employee (agent) relationship.",
            "Principal = the boss who hires the agent. In real estate: seller/landlord = principal. Agent = the one who does the work.",
            "Students confuse 'principal' with the estate agent themselves — thinking the 'main person doing the work' is the principal. In law, principal = the CLIENT who appoints the agent.",
            new String[][]{
                {"A", "The estate agent acting in the transaction", "false"},
                {"B", "The client who appoints the agent to act on their behalf", "true"},
                {"C", "The buyer who makes an offer on the property", "false"},
                {"D", "The bank financing the transaction", "false"}
            });

        saveQ(agencyLaw, Paper.PAPER_2,
            "What is a 'co-broke' arrangement in Singapore real estate?",
            "MEDIUM", "HIGH",
            "A co-broke arrangement is when two agents from different agencies cooperate in a transaction — one representing the seller and one representing the buyer. Commission is typically split between both agents.",
            "A co-broke happens when two agents from DIFFERENT agencies work together on one deal. One represents the seller, one represents the buyer. They split the commission. Very common in Singapore.",
            "Co-broke = cooperation between different agencies = commission split. Two agencies, one deal, shared commission.",
            "Option A says 'same agency' — wrong. Co-broke specifically means DIFFERENT agencies working together. Option D (no commission) is the opposite of what happens.",
            new String[][]{
                {"A", "When two agents from the same agency share a listing", "false"},
                {"B", "When agents from different agencies cooperate and share commission on a deal", "true"},
                {"C", "When an agent brokers a commercial and residential deal simultaneously", "false"},
                {"D", "When an agent acts for both parties without commission", "false"}
            });

        saveQ(agencyLaw, Paper.PAPER_2,
            "An agent owes a fiduciary duty to their client. Which of the following best describes this duty?",
            "MEDIUM", "HIGH",
            "A fiduciary duty requires the agent to act in the best interests of the client, with loyalty and good faith. This includes duties of disclosure, confidentiality, and avoiding conflicts of interest.",
            "Fiduciary duty = the highest level of trust. It means: put the client first, always be honest, don't hide relevant information, keep their details private, and never use your position to benefit yourself.",
            "Fiduciary = complete loyalty to the client. Like a doctor's duty to patients — you NEVER put your own interest above theirs.",
            "Option A says 'maximise price regardless of client preference' — this actually violates fiduciary duty! Higher price doesn't always equal client's interest. Students fall into this because it sounds helpful.",
            new String[][]{
                {"A", "To maximise the transaction price regardless of client preference", "false"},
                {"B", "To act with utmost loyalty and in the best interest of the client", "true"},
                {"C", "To ensure the transaction completes within 30 days", "false"},
                {"D", "To maintain a professional indemnity insurance policy", "false"}
            });

        saveQ(agencyLaw, Paper.PAPER_2,
            "Under CEA regulations, an estate agent must provide a client with which document before marketing a property?",
            "MEDIUM", "HIGH",
            "Before marketing a property, an estate agent must have a signed Estate Agency Agreement (EAA) with the client. This formalises the agency relationship and sets out the terms including commission.",
            "Before an agent can start marketing your property, they need a signed contract with you — the Estate Agency Agreement (EAA). No signed EAA = the agent cannot legally market the property.",
            "EAA must be signed BEFORE marketing begins. No EAA = no marketing. It's the starting gun for any agency relationship.",
            "Students often pick 'valuation report' thinking agents need to know the property's value first. But the EAA is the regulatory document that must come BEFORE anything else.",
            new String[][]{
                {"A", "A valuation report from a licensed valuer", "false"},
                {"B", "A signed Estate Agency Agreement (EAA)", "true"},
                {"C", "A copy of the property's title deed", "false"},
                {"D", "A mortgage eligibility letter from a bank", "false"}
            });

        saveQ(agencyLaw, Paper.PAPER_2,
            "What is the main purpose of the CEA's prescribed Estate Agency Agreement (EAA)?",
            "EASY", "HIGH",
            "The prescribed EAA standardises the terms of engagement between the client and the estate agent, ensuring transparency in commission rates, scope of service, duration, and the agent's obligations.",
            "The EAA is a standardised contract that clearly sets out what the agent will do, how much commission they get, how long the agreement lasts, and everyone's responsibilities. It protects both sides.",
            "EAA = standardised contract = transparency and protection. It prevents disputes by making everything clear from the start.",
            "Option A says 'allows agents to charge higher rates' — the opposite is true. Standardisation limits arbitrary pricing. Option C (replaces OTP) is completely wrong — they serve different purposes.",
            new String[][]{
                {"A", "To allow agents to charge higher commission rates", "false"},
                {"B", "To standardise and formalise the terms of engagement between agent and client", "true"},
                {"C", "To replace the Option to Purchase document", "false"},
                {"D", "To register the property sale with the Singapore Land Authority", "false"}
            });

        // ── Marketing ────────────────────────────────────────────────────────

        saveQ(marketing, Paper.PAPER_2,
            "Under CEA's advertising guidelines, which of the following is mandatory in a property advertisement?",
            "MEDIUM", "HIGH",
            "CEA requires that property advertisements include the salesperson's name and CEA registration number, the agency name and licence number. This allows consumers to verify the identity and credentials of the advertising agent.",
            "Every property ad in Singapore must include the agent's name and their CEA registration number. This lets buyers check if the agent is actually licensed. No name + no number = non-compliant ad.",
            "Every ad must have: agent name + CEA registration number. Think of it like a doctor's licence number on their clinic signboard.",
            "Option A (owner's NRIC) — owners don't need their personal details in ads. Option C (last transacted price) is useful but not mandatory. The focus is on AGENT credentials.",
            new String[][]{
                {"A", "The owner's full name and NRIC number", "false"},
                {"B", "The salesperson's name and CEA registration number", "true"},
                {"C", "The property's last transacted price", "false"},
                {"D", "The estimated rental yield of the property", "false"}
            });

        saveQ(marketing, Paper.PAPER_2,
            "An agent advertises a property as having a '5-minute walk to MRT' when the actual distance is a 15-minute walk. This is most likely a violation of which principle?",
            "EASY", "HIGH",
            "Making false or misleading statements in property advertisements is a breach of the duty of honesty and the prohibition against misrepresentation. It may also violate the Consumer Protection (Fair Trading) Act.",
            "If an agent lies in an ad — say, claiming 5-minute walk to MRT when it's actually 15 minutes — that's a false statement. It misleads buyers into making decisions based on wrong information. That's misrepresentation.",
            "False fact in ad = misrepresentation. Any lie that affects a buyer's decision = misrepresentation. Distance, amenities, size — all must be accurate.",
            "Students sometimes pick SSD or CPD violations because those are common exam topics. But this is clearly about a FALSE STATEMENT — always misrepresentation.",
            new String[][]{
                {"A", "The HDB resale levy rules", "false"},
                {"B", "The prohibition against false and misleading representations", "true"},
                {"C", "The CPD training requirements", "false"},
                {"D", "The Seller's Stamp Duty regulations", "false"}
            });

        saveQ(marketing, Paper.PAPER_2,
            "What is the primary difference between an exclusive listing and an open listing?",
            "MEDIUM", "HIGH",
            "In an exclusive listing, only the appointed agent can market the property, and the seller typically pays commission even if they find a buyer themselves. An open listing allows multiple agents to market the same property.",
            "Exclusive listing = ONE agent has sole rights to sell your property. Open listing = any agent can try, and only the one who brings a buyer gets paid. Exclusive = commitment. Open = competition.",
            "Exclusive = one agent, guaranteed commission. Open = multiple agents, winner takes all the commission.",
            "Option A says exclusive listings are only for luxury properties — completely fabricated. Any property can be exclusively listed. Option C says sellers pay ALL agents in an open listing — wrong, only the selling agent gets paid.",
            new String[][]{
                {"A", "Exclusive listings are only for luxury properties above $3 million", "false"},
                {"B", "Only one agent markets the property exclusively under an exclusive listing", "true"},
                {"C", "Open listings require the seller to pay commission to all agents involved", "false"},
                {"D", "Exclusive listings do not require a signed agency agreement", "false"}
            });

        saveQ(marketing, Paper.PAPER_2,
            "When advertising a property with a stated price, what obligation does the agent have if the property has already received an offer?",
            "HARD", "MEDIUM",
            "When a property has received an offer, the agent should update marketing materials promptly and disclose the status accurately to avoid misleading potential buyers. Advertising a property as available when it is under offer without disclosure may constitute misrepresentation.",
            "Once a buyer makes an offer on a property, the agent must update the ad to show this. Continuing to advertise it as freely available when it's under offer misleads other buyers — that's misrepresentation.",
            "Property status changes → advertisement must change promptly. Accuracy is ongoing, not just at launch.",
            "Option A ('continue until option is exercised') seems reasonable but is wrong — update status promptly. Option C ('remove all advertising immediately') is too extreme. Update, don't delete.",
            new String[][]{
                {"A", "Continue advertising at the same price until the option is exercised", "false"},
                {"B", "Update the advertisement to reflect the current status accurately", "true"},
                {"C", "Immediately remove all advertising regardless of whether the offer is accepted", "false"},
                {"D", "Advertise the property at a higher price to generate competing offers", "false"}
            });

        saveQ(marketing, Paper.PAPER_2,
            "Under the Personal Data Protection Act (PDPA), what must an agent obtain before collecting a client's personal data?",
            "MEDIUM", "MEDIUM",
            "The PDPA requires organisations including estate agents to obtain consent from individuals before collecting, using, or disclosing their personal data. The purpose of collection must also be disclosed.",
            "Before collecting anyone's personal data (name, phone, NRIC), you must get their CONSENT first and tell them why you're collecting it. This applies to all estate agents. No consent = no collection.",
            "PDPA = must get CONSENT before collecting personal data. Always ask first. Purpose must be stated.",
            "Option C says 'approval from CEA for each collection' — CEA doesn't approve individual data collection. Option D ('notarised letter') is far too formal and completely made up.",
            new String[][]{
                {"A", "A statutory declaration from the client", "false"},
                {"B", "Consent from the individual before collecting their personal data", "true"},
                {"C", "Approval from CEA for each data collection activity", "false"},
                {"D", "A notarised authorisation letter", "false"}
            });

        // ── Negotiation ──────────────────────────────────────────────────────

        saveQ(negotiation, Paper.PAPER_2,
            "In a private property sale, what document is typically issued by the seller to formalise the offer after agreement is reached?",
            "EASY", "HIGH",
            "An Option to Purchase (OTP) is issued by the seller to the buyer, granting the buyer an option to purchase the property within a specified period (typically 14 days for private property). The buyer pays an option fee to obtain the OTP.",
            "After buyer and seller agree on a price, the seller issues the buyer an OTP (Option to Purchase). This gives the buyer the exclusive right to buy within a set period — usually 14 days for private property. Buyer pays an option fee to secure it.",
            "Seller → issues OTP → gives buyer the right to purchase. Think of OTP as a 'reserved' sign on the property.",
            "Students confuse OTP with Sale & Purchase Agreement (SPA). The OTP comes FIRST — it gives you the OPTION. The SPA is the full binding contract signed only when you exercise the option.",
            new String[][]{
                {"A", "A Letter of Intent (LOI)", "false"},
                {"B", "An Option to Purchase (OTP)", "true"},
                {"C", "A Sale and Purchase Agreement (SPA)", "false"},
                {"D", "A Tenancy Agreement (TA)", "false"}
            });

        saveQ(negotiation, Paper.PAPER_2,
            "The option fee for a private residential property transaction is typically what percentage of the purchase price?",
            "MEDIUM", "HIGH",
            "The option fee for private property transactions is typically between 1% to 5% of the purchase price, paid by the buyer when receiving the Option to Purchase. When the option is exercised, a further payment (often making up to 5-10% total) is paid.",
            "When you get an OTP, you pay an option fee of 1% to 5% of the purchase price. This locks in your exclusive right to buy. The fee forms part of your final purchase payment.",
            "Option fee = 1% to 5% of purchase price. Not 0.1%, not 10%. Just 1 to 5 percent.",
            "Option C says 10% — too high for just the option phase. Option D says 20% — that's more like a down payment. The specific range for option fee is 1%-5%.",
            new String[][]{
                {"A", "0.1% of the purchase price", "false"},
                {"B", "1% to 5% of the purchase price", "true"},
                {"C", "10% of the purchase price", "false"},
                {"D", "20% of the purchase price", "false"}
            });

        saveQ(negotiation, Paper.PAPER_2,
            "What happens if a buyer decides NOT to exercise the Option to Purchase within the option period?",
            "MEDIUM", "HIGH",
            "If the buyer does not exercise the OTP within the option period, the option lapses and the buyer forfeits the option fee paid. The seller is then free to sell to another party.",
            "If you get an OTP but don't buy the property within the given time, the option expires. You lose the option fee — there's no refund. The seller keeps the money and can sell to someone else.",
            "Don't exercise OTP = lose option fee. No refund. Seller keeps the money and moves on.",
            "Option A says 'full refund' — wrong, the fee is forfeited. Option C says 'transaction proceeds automatically' — also wrong. Nothing happens automatically if you don't exercise. Silence = lapse.",
            new String[][]{
                {"A", "The buyer gets a full refund of the option fee", "false"},
                {"B", "The option fee is forfeited and the seller can sell to others", "true"},
                {"C", "The transaction proceeds automatically at the end of the option period", "false"},
                {"D", "The buyer can apply to court to extend the option period", "false"}
            });

        saveQ(negotiation, Paper.PAPER_2,
            "What is the purpose of a Letter of Intent (LOI) in a commercial property transaction?",
            "MEDIUM", "MEDIUM",
            "An LOI (or Letter of Offer) in commercial property expresses the buyer's or tenant's intention to proceed with the transaction on specified terms. It is typically non-binding but signals commitment before formal documentation.",
            "In commercial property deals, an LOI is like a serious statement of interest: 'I want to buy/lease this on roughly these terms.' It's usually NOT legally binding — it signals intent before formal contracts.",
            "LOI = non-binding intention letter = pre-contract step. Commercial deals. Think: a serious handshake in writing.",
            "Option A says LOI is legally binding — that's the key trap. LOI is generally NON-binding. The binding contracts come later. This is a classic 'similar concept' confusion.",
            new String[][]{
                {"A", "It is a legally binding contract for the sale of property", "false"},
                {"B", "It expresses intent to transact and outlines preliminary terms before formal contracts", "true"},
                {"C", "It replaces the Option to Purchase for all commercial deals", "false"},
                {"D", "It must be signed by a lawyer to be valid", "false"}
            });

        saveQ(negotiation, Paper.PAPER_2,
            "In a property negotiation, what does 'best and final offer' typically signal?",
            "EASY", "MEDIUM",
            "A 'best and final offer' request signals that the seller wants buyers to submit their highest and most competitive offer, typically in a competitive sale situation. It indicates no further negotiation rounds will follow.",
            "When a seller asks for 'best and final offer,' they're saying: submit your single best price now — there are no more negotiation rounds. It's like a mini-auction where everyone shows their highest card at once.",
            "Best and final = show your highest card NOW. No more rounds. Last chance to beat everyone else.",
            "Option A says 'willing to accept below asking price' — this misreads the phrase entirely. Option C says 'only one buyer allowed' — multiple buyers can all submit best-and-final offers simultaneously.",
            new String[][]{
                {"A", "The seller is willing to accept offers below the asking price", "false"},
                {"B", "The seller is requesting buyers to submit their most competitive offer with no further rounds", "true"},
                {"C", "Only one buyer is allowed to bid for the property", "false"},
                {"D", "The property is being sold at a government-mandated fixed price", "false"}
            });

        // ── Ethics ───────────────────────────────────────────────────────────

        saveQ(ethics, Paper.PAPER_2,
            "A client asks their agent to deliberately omit material defects when advertising their property. What should the agent do?",
            "EASY", "HIGH",
            "An agent must not knowingly misrepresent a property or omit material information that would affect a buyer's decision. The agent should advise the client to disclose defects and refuse to participate in misrepresentation.",
            "If your client tells you to hide a major problem with their property, you cannot do it. Your legal and ethical duty requires honesty. You must advise the client to disclose and refuse to hide the defect.",
            "Material defect → must disclose. Your ethical duty overrides client instructions. Honesty comes before client pleasing.",
            "Option A says comply with the client — the instinct is 'client is boss.' But the client cannot instruct you to commit misrepresentation. Your duty to third parties overrides the client's instruction here.",
            new String[][]{
                {"A", "Comply with the client's instruction to maintain the agency relationship", "false"},
                {"B", "Advise the client that material defects must be disclosed and decline to omit them", "true"},
                {"C", "Report the client to the police immediately", "false"},
                {"D", "Add a disclaimer in the advertisement that buyers must do their own checks", "false"}
            });

        saveQ(ethics, Paper.PAPER_2,
            "What does the CEA's Code of Ethics require when an agent has a personal interest in a property being marketed?",
            "MEDIUM", "HIGH",
            "When an agent has a personal or financial interest in a property (e.g., if the agent or their family owns it), full disclosure must be made to the client. This ensures transparency and avoids undisclosed conflicts of interest.",
            "If an agent has any personal stake in a property — like they own it or their family does — they MUST tell all parties involved. Not disclosing it is a serious ethics violation, regardless of price fairness.",
            "Agent has interest → must DISCLOSE to all parties. Always. No exceptions. Disclosure obligation exists regardless of price fairness.",
            "Option C says 'can proceed if price is fair' — wrong. The disclosure obligation exists regardless of fairness. Option D says 'only if client asks' — wrong, disclosure is proactive, not reactive.",
            new String[][]{
                {"A", "The agent must withdraw from the transaction entirely", "false"},
                {"B", "The agent must disclose their personal interest to all parties", "true"},
                {"C", "The agent can proceed without disclosure if the price is fair", "false"},
                {"D", "The agent only needs to disclose if the client specifically asks", "false"}
            });

        saveQ(ethics, Paper.PAPER_2,
            "Continuing Professional Development (CPD) is required for registered salespersons. What is the primary purpose?",
            "EASY", "HIGH",
            "CPD ensures that salespersons keep their knowledge current with legal changes, market developments, and professional standards. CEA mandates CPD hours to maintain registration.",
            "CPD is mandatory training that all registered agents must complete to keep their CEA registration active. It ensures agents stay updated on new laws, market changes, and professional standards — like a doctor's ongoing training.",
            "CPD = mandatory ongoing training = knowledge stays current = registration maintained. No CPD = no renewal.",
            "Option A says 'generate revenue for CEA' — cynical and wrong. Option C says CPD replaces the RES exam — wrong, CPD is for existing practitioners to maintain their registration, not a substitute for entry qualification.",
            new String[][]{
                {"A", "To generate revenue for CEA through training fees", "false"},
                {"B", "To ensure salespersons maintain up-to-date knowledge and skills", "true"},
                {"C", "To replace the need for the RES examination", "false"},
                {"D", "To rank salespersons by competency for client assignment", "false"}
            });

        saveQ(ethics, Paper.PAPER_2,
            "When should a salesperson refer a client to a lawyer or other professional?",
            "MEDIUM", "HIGH",
            "Salespersons should refer clients to appropriate professionals (lawyers for legal advice, valuers for valuations, mortgage brokers for financing) whenever the matter requires expertise beyond the agent's scope. Acting outside one's competence is a professional ethics issue.",
            "Agents are not lawyers, valuers, or mortgage brokers. When a client needs legal advice or a property valuation, refer them to the right professional. Trying to handle everything yourself when it's outside your expertise is an ethics problem.",
            "Outside your scope = refer to a professional. Know your lane. Agent ≠ lawyer ≠ valuer ≠ mortgage broker.",
            "Option D says 'never refer, agent handles everything' — the opposite of good professional conduct. Option C says 'only for $2 million+ deals' — completely fabricated. The trigger is expertise scope, not deal size.",
            new String[][]{
                {"A", "Only when the transaction involves a disputed property", "false"},
                {"B", "Whenever the matter requires professional expertise beyond the agent's role", "true"},
                {"C", "Only for transactions above $2 million in value", "false"},
                {"D", "Never, as the agent is responsible for all aspects of the transaction", "false"}
            });

        saveQ(ethics, Paper.PAPER_2,
            "Which action would most likely result in disciplinary action by CEA?",
            "MEDIUM", "HIGH",
            "Receiving secret profits (undisclosed commissions or kickbacks from third parties) without client consent is a serious breach of fiduciary duty and professional ethics, and would result in disciplinary action by CEA.",
            "If an agent secretly takes money from a third party (like a developer or mortgage broker) without telling their client, that's called a 'secret profit.' This is a serious breach of trust and will likely lead to CEA disciplinary action.",
            "Secret commission = secret profit = serious breach = disciplinary action. Disclose ALL money received from third parties.",
            "Option A (late CPD) is minor administrative — unlikely to cause serious action. Options C and D are perfectly fine practices. The serious violation is always undisclosed financial benefits from third parties.",
            new String[][]{
                {"A", "Submitting a CPD record one week late", "false"},
                {"B", "Receiving undisclosed commissions from a third party without client consent", "true"},
                {"C", "Using digital platforms to market a property listing", "false"},
                {"D", "Providing a client with comparative market data", "false"}
            });

        // ── Client Management ────────────────────────────────────────────────

        saveQ(clientMgmt, Paper.PAPER_2,
            "What is the first step a salesperson should typically take when engaging a new seller client?",
            "EASY", "HIGH",
            "The first step is to conduct a needs assessment to understand the client's goals, timeline, expectations, and circumstances. This informs the agent's marketing strategy and advice.",
            "When you first meet a seller client, don't rush to list their property. First, listen and understand: What's their timeline? What's their goal? What are their expectations? This needs assessment guides everything.",
            "New client → LISTEN FIRST. Needs assessment before any action. Understand before you act.",
            "Option A says 'immediately list on portals' — classic eager-agent mistake. Listing without understanding the client's situation leads to mismatch and problems. Listen first.",
            new String[][]{
                {"A", "Immediately list the property on all online portals", "false"},
                {"B", "Conduct a needs assessment to understand the client's goals and circumstances", "true"},
                {"C", "Request the client to sign the OTP immediately", "false"},
                {"D", "Obtain a bank valuation before meeting the client", "false"}
            });

        saveQ(clientMgmt, Paper.PAPER_2,
            "A buyer client complains that their agent showed them properties outside their stated budget. This suggests a failure in which aspect?",
            "EASY", "HIGH",
            "Showing properties outside the buyer's stated budget indicates a failure in understanding and respecting the client's needs and constraints. Good client management requires active listening and matching services to client requirements.",
            "If a buyer says their budget is $500,000 and you show them $700,000 properties, you've wasted their time and shown you didn't really listen. Good client management starts with respecting what the client actually told you.",
            "Budget stated = budget respected. Show relevant properties only. Client's words = your constraints.",
            "Options A and C describe different failure types — missing paperwork or undisclosed interest. The complaint here is specifically about showing WRONG properties — that's purely a listening/needs failure.",
            new String[][]{
                {"A", "Failure to obtain the signed Estate Agency Agreement", "false"},
                {"B", "Failure to properly understand and respect the client's stated needs and budget", "true"},
                {"C", "Failure to disclose personal interest in the properties shown", "false"},
                {"D", "Failure to submit CPD training records on time", "false"}
            });

        saveQ(clientMgmt, Paper.PAPER_2,
            "After successfully completing a transaction for a client, what is a good practice for building long-term client relationships?",
            "EASY", "MEDIUM",
            "Following up after transaction completion — checking in on the client's experience, providing useful market updates, and staying in touch — builds long-term relationships and referral potential, which is the foundation of a sustainable real estate practice.",
            "After a successful deal, the best agents don't disappear. They check in, send useful market updates, and stay in touch. This builds trust for future business and referrals. Real estate is a relationship business — the deal is the start, not the end.",
            "Transaction done → relationship continues. Follow up = future business + referrals. Good agents stay in touch.",
            "Option C says 'avoid contacting to not appear pushy' — the opposite of good practice. Professional, relevant follow-up is welcomed by clients. Option A (sign another agreement immediately) is too aggressive and transactional.",
            new String[][]{
                {"A", "Immediately request the client to sign another agency agreement", "false"},
                {"B", "Follow up with the client and provide continued value through market updates and check-ins", "true"},
                {"C", "Avoid contacting the client to prevent appearing pushy", "false"},
                {"D", "Transfer the client file to a junior agent to free up capacity", "false"}
            });

        saveQ(clientMgmt, Paper.PAPER_2,
            "A landlord asks their agent to discriminate against certain nationalities when screening tenants. What should the agent do?",
            "MEDIUM", "MEDIUM",
            "Agents must not assist clients in unlawful discrimination. While landlords have some discretion in tenant selection, systematic discrimination based on nationality, race, or religion may violate Singapore's fair tenancy principles and the agent should decline.",
            "If a landlord tells their agent to reject tenants based on nationality, the agent cannot simply comply. While landlords have some tenant preference rights, systematic racial/national discrimination violates professional ethics — the agent should decline and advise properly.",
            "Discriminatory instruction → decline and advise. Agent cannot facilitate systematic discrimination, even for the client.",
            "Option A says 'comply because client is boss' — client relationship doesn't override professional ethics. Option C says 'report to police immediately' — too extreme as a first response. Advise first.",
            new String[][]{
                {"A", "Comply with the landlord's instruction as the landlord is the client", "false"},
                {"B", "Decline to assist and advise the landlord on fair and lawful tenant selection practices", "true"},
                {"C", "Report the landlord to police before doing anything else", "false"},
                {"D", "Apply the discrimination criteria quietly without documenting it", "false"}
            });

        saveQ(clientMgmt, Paper.PAPER_2,
            "What should an agent do if they discover that a client has provided false information on a mortgage application?",
            "HARD", "HIGH",
            "If an agent discovers that a client has provided false information on a mortgage application, this may constitute fraud. The agent should not assist in or facilitate fraudulent activity. Appropriate steps include advising the client to correct the information and declining to continue if the client refuses.",
            "If you find out your client lied on their mortgage application — say, inflated their income — you cannot ignore it or help cover it up. Your professional duty is to tell the client to fix it, and walk away from the deal if they refuse.",
            "Client fraud discovered → advise to correct + refuse to assist if they won't. You cannot knowingly participate in fraud.",
            "Option A says 'ignore it — not your responsibility.' Wrong — once you know, you have a duty. Option C (help adjust other documents to match) makes you a co-conspirator. Option D (bank will catch it) doesn't remove your ethical duty.",
            new String[][]{
                {"A", "Ignore it as the mortgage application is not the agent's responsibility", "false"},
                {"B", "Advise the client to correct the information and decline to assist with the fraudulent application", "true"},
                {"C", "Help the client adjust the remaining documents to be consistent with the false information", "false"},
                {"D", "Proceed with the transaction as the bank will conduct its own checks", "false"}
            });
    }

    // ─────────────────────────────────────────────────────── Helper Methods ──

    private Topic saveTopic(String name, String slug, Paper paper, String description) {
        return topicRepository.save(Topic.builder()
            .name(name)
            .slug(slug)
            .paper(paper)
            .description(description)
            .build());
    }

    private void saveQ(Topic topic, Paper paper, String questionText, String difficulty,
        String examFrequency, String explanation, String plainEnglish,
        String memoryRule, String examTrap, String[][] options) {

        Question question = Question.builder()
            .topic(topic)
            .paper(paper)
            .questionText(questionText)
            .difficulty(difficulty)
            .examFrequency(examFrequency)
            .explanation(explanation)
            .plainEnglish(plainEnglish)
            .memoryRule(memoryRule)
            .examTrap(examTrap)
            .active(true)
            .build();

        List<AnswerOption> answerOptions = new java.util.ArrayList<>();
        for (String[] opt : options) {
            answerOptions.add(AnswerOption.builder()
                .question(question)
                .optionLabel(opt[0])
                .optionText(opt[1])
                .correct(Boolean.parseBoolean(opt[2]))
                .build());
        }
        question.setAnswerOptions(answerOptions);
        questionRepository.save(question);
    }
}
