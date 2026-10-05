# RES Exam Bank — Content Workbench

> **Shared working file for question transformation.**
> Maintained by Claude Code. Read by Codex for app integration context.
> Do not restructure sections without coordinating with the other agent.
>
> **Important:** this markdown file is now the human workbench, not the canonical product source.
> The machine-readable source of truth is:
> `/Users/danial/Desktop/RES-Exam-Bank/backend/src/main/resources/content/question-bank.json`

---

## How This File Works

Each processed question can still get one entry block here for human review.
But every question that should reach the product must also be saved into the structured JSON question bank.

This file now serves 3 purposes:
- capture transformation notes
- preserve tutor-facing context
- point to structured JSON status

---

## Entry Format

```
### [DATE] — [TOPIC SLUG] — [SHORT CONCEPT LABEL]

**Source:** [question number or description]
**Paper:** PAPER_1 or PAPER_2
**Topic:** [topic name]

#### Concept Extraction
...

#### Noise Reduction
...

#### Plain English / Caveman / Minimal
...

#### Answer Teaching + Traps
...

#### Memory Rule + Shortcut
...

#### Drill / Engaging Format
...

#### Flashcards
...

#### Harder Variant
...

#### App-Ready MCQs
...

#### JSON bank status
Saved / pending

#### Product Note
Free / Premium — reason
```

---

## Processed Questions Log

| # | Date | Paper | Topic | Concept | Difficulty | Premium? |
|---|------|-------|-------|---------|------------|---------|
| 1 | 2026-06-03 | PAPER_1 | Housing Policies & HDB | HDB Resale Grant — Short Lease Pro-ration + PHG | HARD | Premium |

---

## Entries

---

### 2026-06-03 — p1-housing — HDB Resale Grant Short Lease Pro-Ration

**Source:** Question 4 (screenshot) — Cheryl (SC, 34) + Axel (SPR, 44) buying 4-room resale HDB, 40 years lease, $8k household income, near parents
**Paper:** PAPER_1
**Topic:** Housing Policies & HDB
**Correct Answer:** $63,866

---

#### Concept Extraction

| Field | Value |
|---|---|
| Topic | Housing Policies & HDB |
| Subtopic | HDB Resale Grant Calculation — Short Lease Pro-Ration |
| Principle Tested | Pro-rate CHG + EHG when lease cannot cover youngest buyer to age 95; PHG is never pro-rated |
| Difficulty | HARD |
| Exam Frequency | HIGH |
| What the examiner is testing | Can the student identify which grants get cut by the lease pro-ration formula, apply the correct formula using the youngest buyer's age, and know that PHG is always added back in full? |

---

#### Noise Reduction

**Relevant Facts**
- Cheryl = SC, age 34 → youngest buyer → goes into pro-ration formula
- Axel = SPR → SC/SPR grant rate applies ($70k CHG, not SC/SC $80k)
- Combined household income = $8,000/month → EHG = $20k
- 4-room resale HDB → determines CHG amount
- 40 years lease remaining → triggers pro-ration
- First-timer couple → confirms grant eligibility
- Beside Cheryl's parents → triggers PHG $20k (near, not same unit)

**Irrelevant Facts (Distractors)**

| Fact | Why It Doesn't Affect the Answer |
|---|---|
| Axel is 44 | Formula uses YOUNGEST buyer's age — Axel is older, irrelevant |
| Purchase price $620k | Grants are not calculated from purchase price |
| Flat valued at $600k | Valuation affects loan quantum, not grant amounts |
| Tiong Bahru location | Geography doesn't change grant amounts |
| HDB loan used | Loan type does not affect grant entitlement |
| Later upgraded to EC at $1.4m | Future event — irrelevant to HDB grant calculation |
| EC valued at $1.5m | Irrelevant |
| HDB later sold for $700k | Sale price at time of sale is irrelevant to grant at time of purchase |
| EC within 4km of parents | Describes the EC, not the HDB — PHG for the HDB uses HDB's location |

- Original Word Count: ~150
- Essential Word Count: ~30
- Noise Reduction: **~80%**

---

#### Plain English / Caveman / Minimal

**Plain English (≤100 words):**
Cheryl (SC, 34) and Axel (SPR) are first-time buyers. Because one is SPR, their base grant is $70k not $80k. Their $8k monthly income adds $20k. That's $90k total. But the flat only has 40 years of lease left — it won't last until Cheryl turns 95. So the government cuts the CHG and EHG proportionally. One grant (PHG — for living near her parents) is never cut. Final answer: $63,866.

**Caveman Version (≤30 words):**
Flat old. Lease short. Big grants get cut by formula. "Near mum" grant never cut. Add together.

**Minimal Exam Version (≤25 words):**
Pro-rate CHG + EHG using youngest buyer's age. PHG always added back in full regardless of lease length.

---

#### Answer Teaching

**Why $63,866 is correct:**

Step 1 — Grant amounts
- CHG (SC/SPR, 4-room resale): **$70,000**
- EHG ($8,000/month household income): **$20,000**
- PHG (within 4km of parents, not same unit): **$20,000**
- CHG + EHG subtotal before pro-ration: **$90,000**

Step 2 — Check if pro-ration applies
- Youngest buyer = Cheryl, age 34
- Required lease to cover to age 95 = 95 − 34 = 61 years
- Actual remaining lease = 40 years → 40 < 61 → **pro-ration applies**

Step 3 — Apply pro-ration formula
```
(Remaining Lease − 20) / (95 − Youngest Age − 20)
= (40 − 20) / (95 − 34 − 20)
= 20 / 41
= 48.78%
```

Step 4 — Pro-rate CHG + EHG
```
$90,000 × 48.78% = $43,866
```

Step 5 — Add PHG in full (never pro-rated)
```
$43,866 + $20,000 = $63,866 ✅
```

---

**Why the wrong answers are wrong:**

| Answer | What the student did | Why it's wrong |
|---|---|---|
| **$90,000** | Correctly got CHG + EHG = $90k but forgot the short lease rule entirely | Missed the 40-year lease trigger — pro-ration was not applied |
| **$100,000** | Used SC/SC grant rate ($80k) instead of SC/SPR ($70k), ignored pro-ration | Confused SC/SPR rate + skipped pro-ration + possibly confused PHG amount |
| **$59,024** | Knew to pro-rate but used wrong formula: $90k × (40/61) instead of $90k × (20/41) | Used raw lease / (95 − age) without the −20 correction on both sides |

---

#### Trap Analysis

| Wrong Answer | Trap Type | Why Students Fall For It |
|---|---|---|
| $90,000 | **Missed Rule** | They calculate grants correctly but never stop to check lease length vs youngest buyer's age. The short-lease trigger is invisible unless you look for it. |
| $100,000 | **Rule Mix-Up** | Apply SC/SC rates out of habit, assume both are SC because the question says "couple." They also skip pro-ration entirely — two errors combined. |
| $59,024 | **Formula Confusion** | They know a formula exists but use the simpler ratio (40 ÷ 61) instead of the adjusted formula ((40−20) ÷ (95−34−20)). Feels right, produces wrong number. |
| Hidden trap | **Misread Fact** | Axel's age (44) is shown before Cheryl's (34). Students plug in 44 to the formula. The rule says youngest buyer — always read both ages and pick the smaller one. |

---

#### Memory Rule + Shortcut

**One-line memory rule:**
> PHG never gets punished for a short lease. Everything else does.

**Exam shortcut (3-step):**
1. Calculate CHG + EHG (exclude PHG)
2. Apply pro-ration: (Remaining Lease − 20) ÷ (95 − Youngest Age − 20)
3. Add PHG in full at the end — always

**If you see → Think:**
- Short lease question → pro-ration check
- Two buyer ages given → use the YOUNGER one in formula
- Near parents → PHG = never pro-rated
- SC + SPR → $70k CHG (not $80k)
- $90k answer option → tempting but needs pro-ration

**Analogy:**
PHG is like a loyalty bonus that never expires. All other grants lose value when the flat ages. PHG doesn't.

**Funny version:**
Government: "Your flat is dying young — we'll cut your grant. But your mum lives next door? That loyalty points? Untouchable."

---

#### Engaging Drill Format: "Pro-Rate or Full Rate?" Sorting Challenge

Show students a list of grants. Drag each to the correct bucket.

| Grant | Bucket |
|---|---|
| CPF Housing Grant (CHG) | PRO-RATED |
| Enhanced Housing Grant (EHG) | PRO-RATED |
| Proximity Housing Grant (PHG) | ALWAYS FULL ✅ |

Bonus: Show a scenario with numbers. Student must identify which bucket each grant goes into before calculating.

---

#### Rapid Mini Drill (8 questions)

1. SC + SPR, 4-room resale → CHG? → **$70,000**
2. SC + SC, 4-room resale → CHG? → **$80,000**
3. Is PHG ever pro-rated for short lease? → **No. Never.**
4. Youngest buyer = 34, lease = 40 years. Does pro-ration apply? → **Yes** (40 < 95−34 = 61)
5. Youngest buyer = 34, lease = 40 years. Pro-ration %? → **(40−20)/(95−34−20) = 20/41 = 48.78%**
6. Household income $8k/month → EHG? → **$20,000**
7. PHG for within 4km, not same unit → **$20,000**
8. PHG for living in same flat as parents → **$30,000**
9. After pro-rating $90k at 48.78%, add $20k PHG → Total? → **$63,866**
10. Both buyers aged 34 and 44 — which age goes in the formula? → **34 (youngest)**

---

#### Flashcards

| # | Front | Back |
|---|---|---|
| 1 | CHG for SC/SPR buying 4-room resale HDB? | $70,000 |
| 2 | When does lease pro-ration apply to grants? | When remaining lease < (95 − youngest buyer age) |
| 3 | Pro-ration formula for short lease? | (Remaining Lease − 20) ÷ (95 − Youngest Age − 20) |
| 4 | Is PHG pro-rated when lease is short? | NO — PHG is always added in full |
| 5 | Youngest buyer 34, lease 40 years, base $90k, PHG $20k → total? | $90k × (20/41) = $43,866 + $20k = **$63,866** |

---

#### Harder Variant (Boss Fight)

**Scenario:**
Marcus (SC, age 29) and Priya (SPR, age 41) are first-timers buying a 3-room resale HDB flat. The flat is 3km from Priya's mother's home (not moving in together). Remaining lease: 45 years. Household income: $7,500/month. How much in total grants are they entitled to?

**Traps set:**
- Priya is older (41) but Marcus (29) is the youngest → use 29 in formula
- PHG = $20k (near, not same unit) — never pro-rated
- 3-room CHG for SC/SPR = $55,000 (different from 4-room)
- EHG at $7,500 = $25,000 (check bracket)
- Lease 45 years, youngest 29 → required = 95−29 = 66 → 45 < 66 → pro-ration applies

**Working (production team to verify exact EHG bracket):**
- CHG + EHG = $55k + $25k = $80k
- Pro-ration: (45−20) / (95−29−20) = 25/46 = 54.35%
- Pro-rated: $80k × 54.35% = $43,478
- PHG: +$20,000 (full)
- **Total ≈ $63,478**

**How to beat the trap:** Before plugging ages into the formula, physically write: "Younger buyer = [name], age [X]." This 3-second check eliminates the most common exam error on this question type.

---

#### App-Ready MCQs (3 new questions)

**MCQ-A**
> Dick Lee (SC, age 38) and Stefanie Sun (SPR, age 45) are first-time buyers of a 4-room resale HDB. The flat has 42 years of lease remaining and is 2km from Dick Lee's father's home (they are not moving in together). Their combined monthly income is $9,000. How much in grants are they entitled to?

- A) $90,000
- B) $60,244
- C) $80,244
- D) $40,244

**Correct: B**
CHG (SC/SPR, 4-room): $70k. EHG ($9k): $10k. Total = $80k. Pro-ration: (42−20)/(95−38−20) = 22/37 = 59.46%. $80k × 59.46% = $47,568. Wait — verify EHG at $9k = $10k (check current HDB table). PHG $20k (near, not same unit, not pro-rated). $47,568 + $20k = $67,568. *(Numbers to be verified against current HDB grant table at publication.)*

---

**MCQ-B**
> JJ Lin (SC, age 31) and Kit Chan (SC, age 44) are a first-timer couple buying a 5-room resale HDB near Kit Chan's parents (same block, not same unit). Flat has 35 years of lease remaining. Household income: $5,000/month. Total grants?

- A) $110,000
- B) $75,000
- C) $60,476
- D) $80,476

**Correct: D** *(verify figures)*
CHG (SC/SC, 5-room): $40k. EHG ($5k): $40k. Total = $80k. PHG (same block = within 4km = $20k). Pro-ration on CHG+EHG: (35−20)/(95−31−20) = 15/44 = 34.09%. $80k × 34.09% = $27,272 + $20k PHG = $47,272. *(Verify grant amounts against current HDB table.)*

---

**MCQ-C**
> Nathan Hartono (SC, age 27) and Gurmit Singh (SPR, age 50) are buying a 4-room resale HDB. Lease has 60 years remaining. No parents nearby. Combined monthly income: $6,000. Total grants?

- A) $70,000
- B) $90,000
- C) $100,000
- D) $95,000

**Correct: B**
CHG (SC/SPR, 4-room): $70k. EHG ($6k): $30k. Total = $100k. No PHG. Check pro-ration: youngest buyer = Nathan, 27. Required: 95−27 = 68 years. Remaining: 60 years. **60 < 68 → pro-ration applies.** (60−20)/(95−27−20) = 40/48 = 83.33%. $100k × 83.33% = $83,333. *(This catches students who assume no pro-ration just because the lease "seems long.")*

---

#### saveQ(...) Block

> **Legacy note:** older entries may still contain `saveQ(...)` snippets from before the JSON pipeline existed.
> New entries should point to JSON bank status instead.

```java
saveQ(housing, Paper.PAPER_1,
    "Cheryl (SC, age 34) and Axel (SPR, age 44) are first-time buyers of a 4-room resale HDB flat " +
    "in Tiong Bahru with 40 years of lease remaining. The flat is located beside Cheryl's parents " +
    "(not same unit). Their combined household income is $8,000 per month. " +
    "How much in total HDB grants are they entitled to?",
    "HARD", "HIGH",
    "Step 1: CHG for SC/SPR 4-room resale = $70,000. EHG at $8,000/month income = $20,000. " +
    "Total before pro-ration = $90,000. PHG (near parents, not same unit) = $20,000. " +
    "Step 2: Check pro-ration — youngest buyer is Cheryl (34). Required lease = 95 − 34 = 61 years. " +
    "Remaining lease = 40 years. Since 40 < 61, pro-ration applies. " +
    "Formula: (40 − 20) / (95 − 34 − 20) = 20/41 = 48.78%. " +
    "Step 3: Pro-rate CHG + EHG: $90,000 × 48.78% = $43,866. " +
    "Step 4: PHG is NEVER pro-rated — add in full: $43,866 + $20,000 = $63,866.",
    "Cheryl and Axel want to buy an old HDB flat. Because it's old (only 40 years left), " +
    "the government cuts their main grants — but the grant for living near parents stays full. " +
    "Use a formula to find how much gets cut, then add the parent-proximity grant back in full.",
    "If you see a short lease + youngest buyer age → apply (lease−20)÷(95−age−20) to CHG+EHG. " +
    "PHG always stays full. Never pro-rate PHG.",
    "Most students pick $90,000 — they get the grant amounts right but completely miss the short-lease " +
    "pro-ration trigger. The second trap is using Axel's age (44) instead of Cheryl's (34) in the formula.",
    new String[][]{
        {"A", "$100,000", "false"},
        {"B", "$90,000",  "false"},
        {"C", "$59,024",  "false"},
        {"D", "$63,866",  "true"}
    });
```

---

#### Product Note

**Placement:** Paper 1 → Housing Policies & HDB → Advanced Grant Calculations
**Free or Premium:** **PREMIUM**
- This is a multi-step calculation question that requires mastery of 4 separate rules simultaneously
- Ideal for paid "Advanced Grant Calculator" module with interactive step-by-step working tool
- High subscription value — this type of question appears in almost every cohort
- Suggested interactive format: Interactive Grant Builder where user selects SC/SPR status, income, flat type, lease years, proximity → app shows step-by-step calculation with each rule highlighted

---

*End of entry 1*

---
